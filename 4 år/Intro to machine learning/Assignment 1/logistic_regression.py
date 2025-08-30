import numpy as np
import pandas as pd
import matplotlib.pyplot as plt

class LogisticRegression:
    def __init__(self, learning_rate=0.01, epochs=1000):
        self.learning_rate = learning_rate
        self.epochs = epochs
        self.weights, self.bias = None, None
        self.losses = []  
        self.train_accuracies = []  
        self.threshold = 0.5

    def sigmoid_function(self, z):
        return 1 / (1 + np.exp(-z))

    def _compute_loss(self, y, y_pred):
        # Compute the binary cross-entropy loss
        # y = true labels, y_pred = predicted probabilities
        # m = number of samples (rows)
        m = y.shape[0]
        # Binary cross-entropy loss formula: - (1/m) * sum(y*log(y_pred) + (1-y)*log(1-y_pred))
        loss = -(1/m) * np.sum(y * np.log(y_pred) + (1 - y) * np.log(1 - y_pred))
        return loss

    def compute_gradients(self, x, y, y_pred):
        m = y.shape[0]
        
        # Create quadratic and interaction terms (since linear clearly failed)
        x_quad = np.hstack((x, x**2, np.prod(x, axis=1).reshape(-1, 1)))
        # Calculate the gradients
        dw = (1/m) * np.dot(x_quad.T, (y_pred - y))
        db = (1/m) * np.sum(y_pred - y)
        
        return dw, db

    def update_parameters(self, grad_w, grad_b):
        self.weights -= self.learning_rate * grad_w
        self.bias -= self.learning_rate * grad_b

    def fit(self, x, y):
        # Create quadratic and interaction terms
        x_quad = np.hstack((x, x**2, np.prod(x, axis=1).reshape(-1, 1)))
    
        # Initialize weights to match the number of features in x_quad
        self.weights = np.zeros(x_quad.shape[1])
        self.bias = 0
        
        for epoch in range(self.epochs):
            # Use x_quad here instead of x
            lin_model = np.dot(x_quad, self.weights) + self.bias
            y_pred = self.sigmoid_function(lin_model)
            grad_w, grad_b = self.compute_gradients(x, y, y_pred)
            self.update_parameters(grad_w, grad_b)
            loss = self._compute_loss(y, y_pred)
            pred_to_class = [1 if _y > self.threshold else 0 for _y in y_pred]
            accuracy = np.mean(y == pred_to_class)
            self.train_accuracies.append(accuracy)
            self.losses.append(loss)

    def predict(self, x):
        # Create quadratic and interaction terms for prediction as well
        x_quad = np.hstack((x, x**2, np.prod(x, axis=1).reshape(-1, 1)))
        lin_model = np.dot(x_quad, self.weights) + self.bias
        y_pred = self.sigmoid_function(lin_model)
        return [1 if _y > self.threshold else 0 for _y in y_pred]
    
    def predict_proba(self, x):
        # Create quadratic and interaction terms for probability prediction as well
        x_quad = np.hstack((x, x**2, np.prod(x, axis=1).reshape(-1, 1)))
        lin_model = np.dot(x_quad, self.weights) + self.bias
        y_pred = self.sigmoid_function(lin_model)
        return y_pred  # Return probabilities
