import rlgym
from stable_baselines3 import PPO
from stable_baselines3.common.vec_env import DummyVecEnv
from stable_baselines3.common.evaluation import evaluate_policy
from custom_reward import CustomReward
from custom_obs import CustomObs

# Create the environment with the custom reward and observation builder
env = rlgym.make(
    reward_fn=CustomReward(),
    obs_builder=CustomObs()
)
env = DummyVecEnv([lambda: env])

# Load an existing model or create a new one
try:
    model = PPO.load("model", env=env)
    print("Loaded existing model.")
except FileNotFoundError:
    model = PPO(
        "MlpPolicy",
        env,
        verbose=1,
        learning_rate=0.0001,  # Lower learning rate for stability
        ent_coef=0.02,         # Encourage exploration
    )
    print("Created a new model.")

# Train the model
model.learn(total_timesteps=1_000_000)

# Save the trained model
model.save("model")
print("Training completed and model saved!")

# Evaluate the model after training
mean_reward, std_reward = evaluate_policy(model, env, n_eval_episodes=10)
print(f"Evaluation Results -> Mean Reward: {mean_reward}, Std Reward: {std_reward}")
