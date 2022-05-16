import numpy as np
import pandas as pd
import xgboost as xgb
import seaborn as sns
import matplotlib.pyplot as plt

from sklearn.model_selection import train_test_split
from sklearn.linear_model import LogisticRegression
from sklearn.tree import DecisionTreeClassifier
from sklearn.ensemble import RandomForestClassifier, BaggingClassifier, AdaBoostClassifier, VotingClassifier
from sklearn.svm import SVC


# ----- Read the txt file and store results to a data frame -----
df_os = pd.read_table("/Users/f5sal/Documents/MSc/CST-590/Capstone Project/OfferDS.txt")

# ----- Perform basic validations on the dataset -----
print("Shape is : \n", df_os.shape)
print("Data Types are : \n", df_os.dtypes)
print("Null rows count is : \n", df_os.isnull().sum())

# ----- Visualize the correlations between the categorical variables -----
# sns.heatmap(df_os.corr(), annot=True, fmt='.0%')
# plt.show()

# ----- Feature selection -----
categorical_var = list(df_os.dtypes.loc[df_os.dtypes == 'object'].index)
for var in categorical_var:
    df_os[var] = df_os[var].astype('category')
df_os[categorical_var] = df_os[categorical_var].apply(lambda x: x.cat.codes)

# ----- Extract the dependent and independent variables -----
X = df_os.iloc[:, :-1].values
y = df_os.iloc[:, 12].values

# ----- Splitting dataset into training and test data -----
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.25)

# ----- Bagging classifier -----
model_bg = BaggingClassifier()
model_bg.fit(X_train, y_train)
print(model_bg.predict(X_test))
print("Bagging Classifier Score is: ", model_bg.score(X_test, y_test))

# ----- Boosting classifier -----
model_xgb = xgb.XGBClassifier(learning_rate=0.1, max_depth=5, n_estimators=5000,
                              subsample=0.5, colsample_bytree=0.5, eval_metric='auc', verbosity=1)
model_xgb.fit(X_train, y_train)
print(model_xgb.predict(X_test))
print("Boosting Classifier Score is: ", model_xgb.score(X_test, y_test))

# ----- Stack / Voting classifier -----
lr = LogisticRegression()
dt = DecisionTreeClassifier()
svm = SVC(kernel='poly', degree=2)
rf = RandomForestClassifier()
model_evc = VotingClassifier(estimators=[('lr', lr), ('dt', dt), ('svm', svm), ('rf', rf)], voting='hard')
model_evc.fit(X_train, y_train)
print(model_evc.predict(X_test))
print("Voting Classifier Score is: ", model_evc.score(X_test, y_test))