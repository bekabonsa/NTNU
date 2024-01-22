import numpy as np

# Function to normalize a vector
def normalize(v):
    norm = np.linalg.norm(v)
    if norm == 0:
        return v
    return v / norm

# Function to find the dominant eigenvalue and eigenvector using the power iteration method
def power_iteration(A, num_simulations: int):
    # Randomly choose a vector to start with
    b_k = np.random.rand(A.shape[1])

    for _ in range(num_simulations):
        # Calculate the matrix-by-vector product Ab
        b_k1 = np.dot(A, b_k)

        # Calculate the norm
        b_k1_norm = np.linalg.norm(b_k1)

        # Re normalize the vector
        b_k = b_k1 / b_k1_norm

    # Rayleigh quotient for the eigenvalue
    eigenvalue = np.dot(b_k.T, np.dot(A, b_k)) / np.dot(b_k.T, b_k)

    return eigenvalue, b_k

# Example of a 4x4 matrix
A = np.array([[1.88559256071, -2.50172462346, 2.60474302152, -0.41200023014],
              [1.47160214507, -1.96760610326, 2.18699705144, -0.345924062783],
              [2.14744720894, -3.0651723665, 3.32428748549, 0.504792457402],
              [2.74105467005, -3.91246173351, 4.07357280725, -0.51143503011]])

# Number of iterations to perform
num_simulations = 10000

# Calculate the dominant eigenvalue and eigenvector
eigenvalue, eigenvector = power_iteration(A, num_simulations)

print("Dominant Eigenvalue:", eigenvalue)
print("Dominant Eigenvector:", eigenvector)
