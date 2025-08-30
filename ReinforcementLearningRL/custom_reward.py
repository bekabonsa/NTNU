from rlgym.utils.reward_functions import RewardFunction
from rlgym.utils.gamestates import GameState, PlayerData
import numpy as np

class CustomReward(RewardFunction):
    def __init__(self):
        self.previous_score = 0
        self.previous_ball_goal_dist = None

    def reset(self, initial_state: GameState):
        self.previous_score = initial_state.blue_score - initial_state.orange_score
        self.previous_ball_goal_dist = self._distance_to_goal(initial_state)

    def _distance_to_goal(self, state: GameState):
        """Calculate distance from ball to opponent's goal."""
        ball_pos = state.ball.position
        goal_pos = np.array([0, 5120, 0])  # Opponent's goal center
        return np.linalg.norm(ball_pos - goal_pos)

    def _alignment(self, vector1, vector2):
        """Calculate alignment using the dot product."""
        norm_v1 = vector1 / (np.linalg.norm(vector1) + 1e-6)
        norm_v2 = vector2 / (np.linalg.norm(vector2) + 1e-6)
        return np.dot(norm_v1, norm_v2)

    def get_reward(self, player: PlayerData, state: GameState, previous_action):
        reward = 0

        # Current score
        current_score = state.blue_score - state.orange_score

        # Scoring or getting scored on
        if current_score > self.previous_score:
            reward += 100  # Goal scored
            self.previous_score = current_score

        if current_score < self.previous_score:
            reward -= 100  # Opponent scores
            self.previous_score = current_score

        # Touching the ball
        if player.ball_touched:
            reward += 10

            # Ball alignment with the goal
            ball_pos = state.ball.position
            goal_pos = np.array([0, 5120, 0])
            ball_dir = goal_pos - ball_pos

            # Car-to-ball alignment
            car_pos = player.car_data.position
            car_dir = ball_pos - car_pos
            alignment_score = self._alignment(ball_dir, car_dir)
            reward += 20 * alignment_score

        # Boost collection
        if player.boost_amount > 0.8:
            reward += 3

        # Ball distance reduction
        current_ball_goal_dist = self._distance_to_goal(state)
        if current_ball_goal_dist < self.previous_ball_goal_dist:
            reward += 15  # Ball closer to the goal
        self.previous_ball_goal_dist = current_ball_goal_dist

        return reward
