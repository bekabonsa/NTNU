from collections import deque
import time
from tqdm import tqdm


class CSP:
    def __init__(
        self,
        variables: list[str], #Example: variables = ['WA', 'NT', 'Q', 'NSW', 'V', 'SA', 'T']
    domains: dict[str, set], #Example: domains = {'WA': {'red', 'green', 'blue'},'NT': {'red', 'green', 'blue'},'Q': {'red', 'green', 'blue'}...}
        edges: list[tuple[str, str]], #Example: edges = [('WA', 'NT'), ('NT', 'Q'), ('SA', 'WA'), ...]
    ):
        """Constructs a CSP instance with the given variables, domains and edges.
        
        Parameters
        ----------
        variables : list[str]
            The variables for the CSP
        domains : dict[str, set]
            The domains of the variables
        edges : list[tuple[str, str]]
            Pairs of variables that must not be assigned the same value
        """
        self.variables = variables
        self.domains = domains
        self.backtrack_count = 0
        self.backtrackFailure_count = 0
        self.bt_time = None
        self.bt_ac3_runtime = None
        
        self.progress_bar = tqdm(desc="Backtracking Progress")

        self.binary_constraints: dict[tuple[str, str], set] = {}
        for variable1, variable2 in edges:
            self.binary_constraints[(variable1, variable2)] = set()
            self.binary_constraints[(variable2, variable1)] = set()  # In the other direction as well
            for value1 in self.domains[variable1]:
                for value2 in self.domains[variable2]:
                    if value1 != value2:
                        self.binary_constraints[(variable1, variable2)].add((value1, value2))
                        self.binary_constraints[(variable2, variable1)].add((value2, value1))   # In the other direction as well
                        
        #print(self.binary_constraints)                
       


    def backtracking_search(self):
        self.bt_time = time.time()
     
        return self.backtrack({})

    def backtrack(self, assignment):
        self.backtrack_count += 1

        self.progress_bar.update(1)
        #if assignment is complete then return assignment
        if len(assignment) == len(self.variables):
            self.bt_time = (time.time() - self.bt_time)*1000
            self.bt_ac3_runtime =  (time.time() - self.bt_ac3_runtime)*1000
            return assignment  # Solution found

        var = self.select_unassigned_variable(assignment)
        for value in self.domains[var]:
            if self.is_consistent(var, value, assignment):
                assignment[var] = value
                result = self.backtrack(assignment)
                if result is not None:
                    return result
                del assignment[var]  # Backtrack
        self.backtrackFailure_count += 1                
        return None  # Failure



    def ac_3(self):
        self.bt_ac3_runtime =  time.time()
        queue = deque(self.binary_constraints.keys())  # A queue of arcs, initially all the arcs in csp
        while queue:
            (Xi, Xj) = queue.popleft()
            if self.revise(Xi, Xj):
                if not self.domains[Xi]:
                    return False  # Failure of task due to empty domain (no available answers left for a variable)
                for neighbor in self.variables:
                    if neighbor != Xi and (neighbor, Xi) in self.binary_constraints:  
                        queue.append((neighbor, Xi))

                     
        return True  # CSP is arc-consistent

    def revise(self, Xi, Xj):
        revised = False
        Di = set(self.domains[Xi])  # Get domain of Xi
        Dj = self.domains[Xj]  # Get domain of Xj
        constraints = self.binary_constraints.get((Xi, Xj), set())  # Get the constraint set between Xi and Xj

        for x in Di:
            satisfies_constraint = False  # Assume no value in Dj satisfies the constraint initially
            
            for y in Dj:
                if (x, y) in constraints:  # Check if the pair (x, y) satisfies the constraint
                    satisfies_constraint = True  # If we find one valid pair, we set this to True
                    break  # No need to check further, so break out of the loop
            
            if not satisfies_constraint:  # If no value y in Dj satisfies the constraint for x
                self.domains[Xi].remove(x)  # Remove x from Xi's domain
                
                revised = True  # Mark that the domain has been revised
        
        return revised


    def select_unassigned_variable(self, assignment):
        for var in self.variables:
            if var not in assignment:
                return var
        return None


    def is_consistent(self, var, value, assignment):
        for (var1, var2) in self.binary_constraints:
            if var1 == var and var2 in assignment:
                if (value, assignment[var2]) not in self.binary_constraints[(var1, var2)]:
                    return False
            elif var2 == var and var1 in assignment:
                if (assignment[var1], value) not in self.binary_constraints[(var1, var2)]:
                    return False
        return True



    

def alldiff(variables: list[str]) -> list[tuple[str, str]]:
        """Returns a list of edges interconnecting all of the input variables
        
        Parameters
        ----------
        variables : list[str]
            The variables that all must be different

        Returns
        -------
        list[tuple[str, str]]
            List of edges in the form (a, b)
        """
        return [(variables[i], variables[j]) for i in range(len(variables) - 1) for j in range(i + 1, len(variables))]
















