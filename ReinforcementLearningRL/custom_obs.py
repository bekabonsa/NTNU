from rlgym.utils.obs_builders import ObsBuilder
import numpy as np

class CustomObs(ObsBuilder):
    def reset(self, initial_state):
        """Reset observation tracking."""
        pass

    def build_obs(self, player, state, previous_action):
        """Build a custom observation array."""
        car_pos = player.car_data.position
        car_vel = player.car_data.linear_velocity
        ball_pos = state.ball.position
        ball_vel = state.ball.linear_velocity
        boost_amount = player.boost_amount

        # Example Custom Observation Array
        obs = np.concatenate([
            car_pos,           # Car Position (x, y, z)
            car_vel,           # Car Velocity (x, y, z)
            ball_pos,          # Ball Position (x, y, z)
            ball_vel,          # Ball Velocity (x, y, z)
            [boost_amount],    # Boost amount
        ])

        return obs
