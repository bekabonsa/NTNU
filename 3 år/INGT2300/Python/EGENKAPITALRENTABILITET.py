def calculate_roe(Rtot, Rd, debt_ratio):
    """
    Calculate the return on equity (ROE) before tax.
    
    Parameters:
    - Rtot (float): Total return on assets before tax (as a decimal).
    - Rd (float): Interest rate on debt (as a decimal).
    - debt_ratio (float): Debt ratio (as a decimal).
    
    Returns:
    - RE (float): Return on equity before tax (as a decimal).
    """
    # Calculate equity portion
    equity_ratio = 1 - debt_ratio
    
    # Rearrange the formula to solve for RE
    RE = (Rtot - (debt_ratio * Rd)) / equity_ratio
    
    return RE

# Example values
Rtot = 0.10  # Total return on assets before tax (10%)
Rd = 0.04    # Interest rate on debt (4%)
debt_ratio = 0.4  # Debt ratio (40%)

# Calculate ROE
RE = calculate_roe(Rtot, Rd, debt_ratio)

# Print the result
print(f"Return on Equity (ROE) before tax: {RE:.3f}")
