import numpy as np

# Define the A-matrix (technology matrix)
A_matrix = np.array([
    [1, -0.1, -100, -50],
    [-0.2, 1000, -80, -8],
    [0, -0.02, 1, 0],
    [0, -0.04, -0.2, 1]
])

# Define the emissions matrix (direct climate emissions per produced unit)
emissions_matrix = np.array([[0.02,0,600,25],
                             [0.03, 0, 3.4, 0.1],
                             [0, 0, 0, 1400]])


f = np.array([[0], [25], [0], [1]])


r = emissions_matrix @ np.linalg.inv(A_matrix)@f
print(r)


