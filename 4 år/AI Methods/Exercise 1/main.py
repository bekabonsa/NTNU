import random
#-----------------------------------------------------------------
# 2C
# Define payouts and their probabilities
payouts = [20, 15, 5, 3, 2, 1]  # Payouts for each outcome
probabilities = [
    1/64,  # BAR/BAR/BAR
    1/64,  # BELL/BELL/BELL
    1/64,  # LEMON/LEMON/LEMON
    1/64,  # CHERRY/CHERRY/CHERRY
    3/64,  # CHERRY/CHERRY/?
    9/64   # CHERRY/?/?
]
# Calculate the probability of losing (no payout)
prob_losing = 1 - sum(probabilities)
payouts.append(0)  # Add 0 payout for losing
probabilities.append(prob_losing)

def compute_median(results):
    """
    Computes the median of a list of results.
    
    Parameters:
    - results: List of numerical values.

    Returns:
    - Median of the list.
    """
    sorted_results = sorted(results)
    n = len(sorted_results)

    if n % 2 == 1:  # Odd-length dataset
        return sorted_results[n // 2]
    else:  # Even-length dataset
        mid1, mid2 = sorted_results[n // 2 - 1], sorted_results[n // 2]
        return (mid1 + mid2) / 2

def simulate_plays(starting_coins, trials=1000000):
    """
    Simulates the number of plays until going broke for a given number of starting coins.
    
    Parameters:
    - starting_coins: Initial number of coins.
    - trials: Number of simulation trials.

    Returns:
    - Mean number of plays until going broke.
    - Median number of plays until going broke.
    """
    results = []  # To store the number of plays per trial

    for _ in range(trials):
        coins = starting_coins
        plays = 0

        while coins > 0:
            plays += 1
            # Randomly determine the outcome based on probabilities
            outcome = random.choices(payouts, probabilities)[0]
            coins += outcome - 1  # Deduct 1 coin for the play

        results.append(plays)

    # Calculate mean and median
    mean_plays = sum(results) / len(results)
    median_plays = compute_median(results)  # Use the refactored median function

    return mean_plays, median_plays


mean, median = simulate_plays(starting_coins=10)
print(f"Mean number of plays before going broke: {mean}")
print(f"Median number of plays before going broke: {median}")

#-------------------------------------------------------------------------------------------------
#Part 1
def simulate_shared_birthday(N, trials=10000):
    """
    Simulates the probability of at least two people sharing a birthday in a group of N.
    
    Parameters:
    - N: Number of people in the group
    - trials: Number of simulation trials (default: 10,000)
    
    Returns:
    - Probability of at least two people sharing a birthday
    """
    shared_count = 0
    
    for _ in range(trials):
        # Generate N random birthdays (1 to 365)
        birthdays = [random.randint(1, 365) for _ in range(N)]
        # Check if there are duplicates
        if len(birthdays) != len(set(birthdays)):
            shared_count += 1
    
    return shared_count / trials


def analyze_birthday_problem(start=10, end=50, threshold=0.5):
    """
    Analyzes the birthday problem for a range of N values to find:
    - The smallest N where the probability is >= 50%.
    - The proportion of N values with a probability >= 50%.
    
    Parameters:
    - start: Start of the range for N (default: 10)
    - end: End of the range for N (default: 50)
    - threshold: Probability threshold (default: 0.5)
    
    Returns:
    - Results as a dictionary
    """
    results = []
    for N in range(start, end + 1):
        prob = simulate_shared_birthday(N)
        results.append((N, prob))
    
    # Find the smallest N where probability >= threshold
    smallest_N = next((N for N, prob in results if prob >= threshold), None)
    
    # Calculate the proportion of N values with probability >= threshold
    proportion = sum(1 for _, prob in results if prob >= threshold) / len(results)
    
    return {
        "results": results,
        "smallest_N": smallest_N,
        "proportion": proportion
    }

# Example usage
analysis = analyze_birthday_problem()
print(f"Smallest N with P >= 50%: {analysis['smallest_N']}")
print(f"Proportion of N in range [10, 50] with P >= 50%: {analysis['proportion']:.2f}")



#-----------------------------------------------------------------------------------
#Part 2

def simulate_days_to_cover_all(trials=100000000):
    """
    Simulates how many people are needed on average to cover all 365 days of the year.

    Parameters:
    - trials: Number of simulation trials to run.

    Returns:
    - Average number of people needed to cover all 365 days.
    """
    total_people = 0
    all_365_included  = True
    for _ in range(trials):
        covered_days = set()  # Set to keep track of covered days
        people = 0

        while len(covered_days) < 365:
            birthday = random.randint(1, 365)  # Random birthday
            covered_days.add(birthday)
            people += 1 
         
        if 1 not in covered_days:
            all_365_included = False
            print(all_365_included)

        total_people += people

    return total_people / trials  # Average number of people needed

# Run the simulation with 1000 trials
average_people_needed = simulate_days_to_cover_all(trials=1000)
print(f"Average number of people needed to cover all 365 days: {average_people_needed}")
