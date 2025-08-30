import time
from copy import deepcopy
from typing import List, Tuple, Union

# Type definitions
State = Tuple[int, List[List[Union[int, None]]]]  # Tuple of player (whose turn it is) and board
Action = Tuple[int, int]  # Where to place the player's piece

# Game class definition
class Game:
    def initial_state(self) -> State:
        """Returns the initial state of the game: Player 1's turn, and an empty board."""
        return (0, [[None, None, None], [None, None, None], [None, None, None]])

    def to_move(self, state: State) -> int:
        """Returns the player whose turn it is."""
        player_index, _ = state
        return player_index

    def actions(self, state: State) -> List[Action]:
        """Returns the list of possible actions (empty cells on the board)."""
        _, board = state
        actions = []
        for row in range(3):
            for col in range(3):
                if board[row][col] is None:
                    actions.append((row, col))
        return actions

    def result(self, state: State, action: Action) -> State:
        """Returns the new state after taking an action (placing a piece on the board)."""
        _, board = state
        row, col = action
        next_board = deepcopy(board)
        next_board[row][col] = self.to_move(state)
        return (self.to_move(state) + 1) % 2, next_board

    def is_winner(self, state: State, player: int) -> bool:
        """Checks if the given player has won."""
        _, board = state
        for row in range(3):
            if all(board[row][col] == player for col in range(3)):
                return True
        for col in range(3):
            if all(board[row][col] == player for row in range(3)):
                return True
        if all(board[i][i] == player for i in range(3)):
            return True
        if all(board[i][2 - i] == player for i in range(3)):
            return True
        return False

    def is_terminal(self, state: State) -> bool:
        """Checks if the game is over (either win or draw)."""
        _, board = state
        if self.is_winner(state, (self.to_move(state) + 1) % 2):
            return True
        return all(board[row][col] is not None for row in range(3) for col in range(3))

    def utility(self, state: State, player: int) -> float:
        """Returns the utility of the current state for the given player."""
        assert self.is_terminal(state)
        if self.is_winner(state, player):
            return 1
        if self.is_winner(state, (player + 1) % 2):
            return -1
        return 0

    def print(self, state: State):
        """Prints the current state of the board."""
        _, board = state
        print()
        for row in range(3):
            cells = [' ' if board[row][col] is None else 'x' if board[row][col] == 0 else 'o' for col in range(3)]
            print(f" {cells[0]} | {cells[1]} | {cells[2]} ")
            if row < 2:
                print(" - + - + -")
        print()
        if self.is_terminal(state):
            if self.utility(state, 0) > 0:
                print("P1 won")
            elif self.utility(state, 1) > 0:
                print("P2 won")
            else:
                print("The game is a draw")
        else:
            print(f"It is P{self.to_move(state) + 1}'s turn to move")

# Alpha-Beta Pruning function
def alpha_beta_search(game: Game, state: State) -> Action:
    """Performs the Alpha-Beta search to find the optimal action for the current player."""
    player = game.to_move(state)

    def max_value(state, alpha, beta):
        if game.is_terminal(state):
            return game.utility(state, player), None
        v = float('-inf')
        best_action = None
        for action in game.actions(state):
            new_state = game.result(state, action)
            min_val, _ = min_value(new_state, alpha, beta)
            if min_val > v:
                v = min_val
                best_action = action
            if v >= beta:
                return v, best_action  # Beta pruning
            alpha = max(alpha, v)
        return v, best_action

    def min_value(state, alpha, beta):
        if game.is_terminal(state):
            return game.utility(state, player), None
        v = float('inf')
        best_action = None
        for action in game.actions(state):
            new_state = game.result(state, action)
            max_val, _ = max_value(new_state, alpha, beta)
            if max_val < v:
                v = max_val
                best_action = action
            if v <= alpha:
                return v, best_action  # Alpha pruning
            beta = min(beta, v)
        return v, best_action

    # Alpha-Beta search starts with the full range of possible values
    _, action = max_value(state, float('-inf'), float('inf'))
    return action

# Self-play loop with total time measurement
if __name__ == "__main__":
    game = Game()
    state = game.initial_state()

    # Start timer for the whole game
    

    while not game.is_terminal(state):
        game.print(state)
        if game.to_move(state) == 0:
            print("Player 1 (x) action...")
        else:
            print("Player 2 (o) action...")
        start_time = time.time()
        # Find the best action using Alpha-Beta Pruning
        action = alpha_beta_search(game, state)
            # End timer after the game finishes
        end_time = time.time()
        total_time = end_time - start_time
        print(f"Total time taken for Alpha-Beta Pruning: {total_time:.6f} seconds")
        # Apply the action and update the state
        state = game.result(state, action)



    # Print the final game state and total time taken
    game.print(state)
   
