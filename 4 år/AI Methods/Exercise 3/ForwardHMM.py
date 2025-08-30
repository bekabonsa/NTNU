# Import the necessary library
import numpy as np

# Define the transition model matrix T (2x2 matrix)
T = np.array([
    [0.7, 0.3],  # New state probabilities when previous state is Rain and No Rain, respectively
    [0.3, 0.7]
])

# Define the sensor model vectors for each observation outcome
# When the umbrella is observed (True):
U_true = np.array([0.9, 0.2])
# When the umbrella is not observed (False):
U_false = np.array([0.1, 0.8])

# Initial state belief f0: equal probability for Rain and No Rain
# P(R0) = 〈0.5, 0.5〉according to the book.
f_current = np.array([0.5, 0.5])

# Define a function to perform the forward update for one time step
def forward_step(current_belief, observed_U):
    """
    Performs one step of the forward (filtering) update.
    
    Parameters:
      current_belief: numpy array, shape (2,)
         The belief vector at time t: [P(Rain), P(No Rain)]
      observed_U: boolean
         The observed evidence at time t+1 (True if umbrella observed, False otherwise)
    
    Returns:
      new_belief: numpy array, shape (2,)
         The updated belief vector at time t+1 after incorporating the new observation.
    """
    # Prediction: compute the predicted state for time t+1 using the transition model
    predicted = T.dot(current_belief)

    #print(f"predicted {predicted}")
    
    # Select the appropriate sensor model based on the observation
    sensor_model = U_true if observed_U else U_false
    
    # Update: perform elementwise multiplication with the sensor model
    updated = predicted * sensor_model  # Elementwise multiplication
    
    # Normalize the updated vector to ensure the probabilities sum to 1
    new_belief = updated / np.sum(updated)
    
    return new_belief

# Example: Verify the implementation for day 1 and day 2 with umbrella observed both days

# Day 1: observation is True (umbrella observed)
f_day1 = forward_step(f_current, True)
# f_day1 should be approximately [0.818, 0.182] according to the book.

# Day 2: again, observed_U is True (umbrella observed)
f_day2 = forward_step(f_day1, True)
# f_day2[0] (for Rain) should be approximately 0.883 according to the book.


print("")


print("Forward message for Day 1:", f_day1)
print("Forward message for Day 2:", f_day2)

# For the function to compute the forward messages for a longer sequence, in the order specified in the task.
# Define the observation sequence as a list of booleans
observed_Us = [True, True, False, True, True]

# Reset the belief to the initial belief for day 0
f_current = np.array([0.5, 0.5])


forward_messages = []  # To store the normalized forward message at each time step

# Iterate through the sequence, updating the forward message at each step
for obs in observed_Us:
    # Recursive part
    f_current = forward_step(f_current, obs)
    forward_messages.append(f_current)
    # Optionally, print the current forward message
    print("Forward message after observation", obs, ":", f_current)