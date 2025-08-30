from typing import List, Tuple, Union, Optional

# Type definitions
State = Tuple[int, List[Union[str, int]]]  # Tuple of player (whose turn it is) and the buckets (as str) or numbers (as int)
Action = Union[str, int]  # Bucket choice (as str) or choice of number

# Game class definition
class Game:
    def initial_state(self) -> State:
        """Returns the initial state of the game: Player 1's turn, and all buckets available."""
        return 0, ['A', 'B', 'C']

    def to_move(self, state: State) -> int:
        """Returns the player whose turn it is."""
        player, _ = state
        return player

    def actions(self, state: State) -> List[Action]:
        """Returns the list of possible actions (buckets or numbers) in the current state."""
        _, actions = state
        return actions

    def result(self, state: State, action: Action) -> State:
        """Returns the new state after taking an action (selecting a bucket or a number)."""
        if action == 'A':
            return (self.to_move(state) + 1) % 2, [-50, 50]
        elif action == 'B':
            return (self.to_move(state) + 1) % 2, [3, 1]
        elif action == 'C':
            return (self.to_move(state) + 1) % 2, [-5, 15]
        # If the action is a number, the game proceeds to terminal state with just that number
        assert isinstance(action, int)
        return (self.to_move(state) + 1) % 2, [action]

    def is_terminal(self, state: State) -> bool:
        """Returns True if the game is in a terminal state (i.e., only one number left)."""
        _, actions = state
        return len(actions) == 1

    def utility(self, state: State, player: int) -> float:
        """Returns the utility of the current state for Player 1."""
        assert self.is_terminal(state)
        _, actions = state
        assert isinstance(actions[0], int)
        return actions[0] if player == self.to_move(state) else -actions[0]

    def print(self, state: State):
        """Prints the current state of the game."""
        print(f'The state is {state} and ', end='')
        if self.is_terminal(state):
            print(f"P1's utility is {self.utility(state, 0)}")
        else:
            print(f'it is P{self.to_move(state) + 1}\'s turn')

# Minimax search function
def minimax_search(game: Game, state: State) -> Optional[Action]:
    """Performs the Minimax search to find the optimal action for the current player."""
    player = game.to_move(state)

    def max_value(state):
        if game.is_terminal(state):
            return game.utility(state, player), None
        v = float('-inf')
        best_action = None
        for action in game.actions(state):
            new_state = game.result(state, action)
            min_val, _ = min_value(new_state)
            if min_val > v:
                v = min_val
                best_action = action
        return v, best_action

    def min_value(state):
        if game.is_terminal(state):
            return game.utility(state, player), None
        v = float('inf')
        best_action = None
        for action in game.actions(state):
            new_state = game.result(state, action)
            max_val, _ = max_value(new_state)
            if max_val < v:
                v = max_val
                best_action = action
        return v, best_action

    # Get the best action from the max_value function
    _, action = max_value(state)
    return action

# Main function to test the solution
if __name__ == "__main__":
    game = Game()
    state = game.initial_state()

    # Print the initial state
    game.print(state)

    # Find the best action for Player 1 (P1) using minimax_search
    action_p1 = minimax_search(game, state)
    print(f"P1's action: {action_p1}")
    
    # Update the state based on Player 1's action
    state = game.result(state, action_p1)
    game.print(state)

    # Now it's Player 2's (P2) turn
    if not game.is_terminal(state):
        action_p2 = minimax_search(game, state)
        print(f"P2's action: {action_p2}")
        
        # Update the state based on Player 2's action
        state = game.result(state, action_p2)
        game.print(state)
