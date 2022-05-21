import sys

import numpy as np
import pandas as pd
import xgboost as xgb
import seaborn as sns
import matplotlib.pyplot as plt
import mysql.connector
from mysql.connector import Error

# *********** LOOP for more than one request...........

from sklearn.model_selection import train_test_split
from sklearn.linear_model import LogisticRegression
from sklearn.tree import DecisionTreeClassifier
from sklearn.ensemble import RandomForestClassifier, BaggingClassifier, AdaBoostClassifier, VotingClassifier
from sklearn.svm import SVC

connection = mysql.connector.connect(host='localhost', database='offerusers', user='root', password='Capstone')


# ----- Function to update the product database based on the decision -----
def db_update(DecisionCode, prdID):
    mycursor = connection.cursor()
    if DecisionCode == "Approved":
        sql = "UPDATE offerusers.all_requests SET m_decision=1 WHERE (m_decision=9)"
        sql_dec = "UPDATE offerusers.request SET req_decision=%s WHERE (productID=%s);"
    else:
        sql = "UPDATE offerusers.all_requests SET m_decision=0 WHERE (m_decision=9)"
        sql_dec = "UPDATE offerusers.request SET req_decision=%s WHERE (productID=%s);"
    # mycursor.execute(sql)
    val = (DecisionCode, prdID)
    mycursor.execute(sql_dec, val)
    connection.commit()
    print(mycursor.rowcount, "record(s) updated")


# ----- Read the txt file and store results to a data frame -----
df_os = pd.read_sql('SELECT * FROM offerusers.all_requests where m_decision!=9', con=connection)
df_new = pd.read_sql('SELECT * FROM offerusers.all_requests where m_decision=9', con=connection)
df_preserve = pd.read_sql('SELECT * FROM offerusers.all_requests where m_decision=9', con=connection)
if df_new.empty:
    print("No product request for decisioning...")
    sys.exit()

# # ----- Perform basic validations on the dataset -----
# print("Shape is : \n", df_os.shape)
# print("Data Types are : \n", df_os.dtypes)
# print("Null rows count is : \n", df_os.isnull().sum())

# ----- Visualize the correlations between the categorical variables -----
# sns.heatmap(df_os.corr(), annot=True, fmt='.0%')
# plt.show()

# ----- Feature selection for baseline records -----
categorical_var = list(df_os.dtypes.loc[df_os.dtypes == 'object'].index)
for var in categorical_var:
    df_os[var] = df_os[var].astype('category')
df_os[categorical_var] = df_os[categorical_var].apply(lambda x: x.cat.codes)
# ----- Feature selection for new record -----
categorical_var = list(df_new.dtypes.loc[df_new.dtypes == 'object'].index)
for var in categorical_var:
    df_new[var] = df_new[var].astype('category')
df_new[categorical_var] = df_new[categorical_var].apply(lambda x: x.cat.codes)

# ----- Extract the dependent and independent variables -----
X = df_os.iloc[:, :-1].values
X_new = df_new.iloc[:, :-1].values
y = df_os.iloc[:, 12].values

# ----- Splitting dataset into training and test data -----
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.25)

# ----- Bagging classifier -----
model_bg = BaggingClassifier()
model_bg.fit(X_train, y_train)
# print(model_bg.predict(X_test))
bagging_score = model_bg.score(X_test, y_test)
# print("Bagging Classifier Score is: ", bagging_score)

# ----- Boosting classifier -----
model_xgb = xgb.XGBClassifier(learning_rate=0.1, max_depth=5, n_estimators=5000,
                              subsample=0.5, colsample_bytree=0.5, eval_metric='auc', verbosity=1)
model_xgb.fit(X_train, y_train)
# print(model_xgb.predict(X_test))
boosting_score = model_xgb.score(X_test, y_test)
# print("Boosting Classifier Score is: ", boosting_score)

# ----- Stack / Voting classifier -----
lr = LogisticRegression()
dt = DecisionTreeClassifier()
svm = SVC(kernel='poly', degree=2)
rf = RandomForestClassifier()
model_evc = VotingClassifier(estimators=[('lr', lr), ('dt', dt), ('svm', svm), ('rf', rf)], voting='hard')
model_evc.fit(X_train, y_train)
# print(model_evc.predict(X_test))
voting_score = model_evc.score(X_test, y_test)
# print("Voting Classifier Score is: ", voting_score)

# ----- Iterate over the number of rows to predict -----
mycursor = connection.cursor()
sql = "SELECT count(*) FROM offerusers.all_requests WHERE (m_decision=9)"
mycursor.execute(sql)
count_rec = mycursor.fetchone()[0]

a = model_bg.predict(X_new)
b = model_xgb.predict(X_new)
c = model_evc.predict(X_new)

for i in range(count_rec):
    print("*** Iteration for product: ", df_preserve.iloc[i].values[0])
    print("New Bagging prediction: ", a[i])
    print("New Boosting prediction: ", b[i])
    print("New Stacking prediction: ", c[i])
    if bagging_score == 1 & a[i] == 1:
        print("Offer Request Approved by Bagging model")
        db_update("Approved", df_preserve.iloc[i].values[0])
    elif boosting_score == 1 & b[i] == 1:
        print("Offer Request Approved by Boosting model")
        db_update("Approved", df_preserve.iloc[i].values[0])
    elif voting_score == 1 & c[i] == 1:
        print("Offer Request Approved by Stacking model")
        db_update("Approved", df_preserve.iloc[i].values[0])
    else:
        print("Offer Request NOT Approved")
        db_update("Denied", df_preserve.iloc[i].values[0])
