import numpy as np
import matplotlib.pyplot as plt
import pandas as pd
class LinearRegression():
    
    
    def __init__(self,learning_rate=0.001, epochs=10):
      

        self.learning_rate = learning_rate
        self.epochs = epochs
        self.a = None
        self.b = None
        self.losses = []
        self.normalizedLosses = []
        
    def fit(self, X, y):
        """
        Estimates parameters for the linear regression model using gradient descent.
        
        Args:
            X (array<m,n>): a matrix of floats with m rows (#samples) and n columns (#features)
            y (array<m>): a vector of floats
            
        Returns:
            self: Fitted model
        """
        # Convert X to a NumPy array if it is a pandas Series
        if isinstance(X, pd.Series):
            X = X.values
        
        # If X is a 1D array, reshape it to be 2D (m, 1)
        if len(X.shape) == 1:
            X = X.reshape(-1, 1)
        
        # Number of training examples (m) and features (n)
        m, n = X.shape
        print(f"Number of samples: {m}, Number of features: {n}")

        # Initialize parameters
        self.a = np.zeros(n)
        #print(X)
        #print(y)
        self.b = 0
        
        # Gradient Descent
        for i in range(self.epochs):
            # Calculate predictions
            y_pred = np.dot(X, self.a) + self.b
            
            # Calculate the Mean Squared Error (MSE) loss
            loss = (1/m) * np.sum((y_pred - y) ** 2)
            
          
            self.losses.append(loss)

            # Calculate the gradients
            da = (2/m) * np.dot(X.T, (y_pred - y))
            #print(y_pred - y)
            db = (2/m) * np.sum(y_pred - y)
            
            # Update parameters
            self.a -= self.learning_rate * da
            self.b -= self.learning_rate * db
        print(self.losses)    
        return self.a, self.b

    
    def predict(self, X):
        """
        Generates predictions using the learned linear model.
        
        Args:
            X (array<m,n>): a matrix of floats with m rows (#samples) and n columns (#features)
            
        Returns:
            A length m array of floats containing the predictions
        """
         # Convert X to a NumPy array if it is a pandas Series
        if isinstance(X, pd.Series):
            X = X.values
        
        # If X is a 1D array, reshape it to be 2D (m, 1)
        if len(X.shape) == 1:
            X = X.reshape(-1, 1)
        
        return np.dot(X, self.a) + self.b
       

        



