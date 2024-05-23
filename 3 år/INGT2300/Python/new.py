def calculate_required_price(fixed_costs, variable_cost_per_unit, quantity, safety_margin_percentage):
    # Calculate Net Profit (NP) based on safety margin
    NP = (1 - safety_margin_percentage / 100) * quantity
    
    # Solve for price (p) using the provided equation
    p = (fixed_costs + NP * variable_cost_per_unit) / NP + variable_cost_per_unit
    
    return p

# Constants
fixed_costs = 100000  # Fixed Costs
variable_cost_per_unit = 5  # Variable Cost per Unit
quantity = 10000  # Quantity
safety_margin_percentage = 10  # Safety Margin Percentage

# Calculate required price per unit
required_price = calculate_required_price(fixed_costs, variable_cost_per_unit, quantity, safety_margin_percentage)

print(f"Required price per unit: {required_price:.5f}")
