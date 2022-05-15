# Machine Learning algorithm for the Offer Decisioning System
import pandas as pd
import numpy as np
import xgboost as xgb
from sklearn.model_selection import train_test_split
from sklearn.model_selection import GridSearchCV
from sklearn.metrics import roc_auc_score
from sklearn import metrics
from sklearn.metrics import mean_squared_error

import matplotlib.pyplot as plt
import seaborn as sns
from sklearn.linear_model import LogisticRegression
from sklearn.neighbors import KNeighborsClassifier



# Read the txt file and store results to a data frame
df_os = pd.read_table("/Users/f5sal/Documents/MSc/CST-590/Capstone Project/OfferDS.txt")

# Perform basic validations on the dataset
# print("Shape is : \n", df_os.shape)
# print("Data Types are : \n", df_os.dtypes)
# print("Null rows count is : \n", df_os.isnull().sum())

# Visualize the correlations between the categorical variables
# sns.heatmap(df_os.corr(), annot=True, fmt='.0%')
# plt.show()

# Feature selection
categorical_var = list(df_os.dtypes.loc[df_os.dtypes == 'object'].index)
for var in categorical_var:
    df_os[var] = df_os[var].astype('category')
df_os[categorical_var] = df_os[categorical_var].apply(lambda x: x.cat.codes)

# Extract the dependent and independent variables
X = df_os.iloc[:, :-1].values
y = df_os.iloc[:, 12].values

# Splitting dataset into training and test data
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.25)

# Fitting multiple linear regression to the training set
model = LogisticRegression().fit(X_train, y_train)
print(model.predict(X_test))
print("Score is: ", model.score(X_test, y_test))

# KNN Regression
model_KNN = KNeighborsClassifier(n_neighbors=8).fit(X_train, y_train)
y_pred_KNN = model_KNN.predict(X_test)
print("KNN Accuracy: ", metrics.accuracy_score(y_test, y_pred_KNN))

# XGBoost
# model_xgb = xgb.XGBRegressor(objective='reg:linear',
#                           colsample_bytree =0.3,
#                           learning_rate =0.1,
#                           max_depth =5, alpha =10,
#                           n_estimators =10)
# model_xgb.fit(X_train,y_train)
# preds = model_xgb.predict_proba(X_test)
# print("Preds: ", preds)
# rmse = np.sqrt(mean_squared_error(y_test, preds))
# print("RMSE: %f" % (rmse))

model_xgboost = xgb.XGBClassifier(learning_rate=0.1,
                                      max_depth=5,
                                      n_estimators=5000,
                                      subsample=0.5,
                                      colsample_bytree=0.5,
                                      eval_metric='auc',
                                      verbosity=1)
eval_set = [(X_test, y_test)]

model_xgboost.fit(X_train,
                  y_train,
                  early_stopping_rounds=10,
                  eval_set=eval_set,
                  verbose=True)

y_train_pred = model_xgboost.predict_proba(X_train)[:,1]
y_test_pred = model_xgboost.predict_proba(X_test)[:,1]

print("AUC Train: {:.4f}\nAUC Valid: {:.4f}".format(roc_auc_score(y_train, y_train_pred),
                                                    roc_auc_score(y_test, y_test_pred)))

