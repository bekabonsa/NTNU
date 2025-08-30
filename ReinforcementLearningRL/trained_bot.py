import rlgym
from stable_baselines3 import PPO

# Load the environment
env = rlgym.make()

# Load the trained model
model = PPO.load("model")

# Test the bot
episodes = 5

for ep in range(episodes):
    obs = env.reset()
    done = False
    score = 0

    while not done:
        action, _states = model.predict(obs)
        obs, reward, done, info = env.step(action)
        score += reward

    print(f"Episode {ep + 1}: Final Score: {score}")
