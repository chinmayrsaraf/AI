# Design fuzzy set for numbers close to 5

import sys
import numpy as np
import matplotlib.pyplot as plt

def calculate_membership(x): #fx to calculate memship
    r = 1
    a = 5 / max(x, 5)
    b = x / max(x, 5)
    y = r * abs(a - b)
    v = 1 - y
    return v

# Input
try:
    x = float(input("Enter number whose membership is to be calculated: "))
except ValueError:
    print("Invalid input. Please enter a valid number.")
    sys.exit(1)

r = 1

# Calculate membership value
a = 5 / max(x, 5)
b = x / max(x, 5)
y = r * abs(a - b)
v = 1 - y

if y == 0:
    print("Full Member")
elif 0 < y < 1:
    print(f"Membership Value: {v:.2f}")
elif y >= 1:
    print("Non Member")

input_values = np.arange(0, 10.1, 0.1) # graph i/p values

membership_values = [calculate_membership(x) for x in input_values]

plt.plot(input_values, membership_values)
plt.xlabel("Input")
plt.ylabel("Membership Value")
plt.title("Fuzzy Set Membership Function")
plt.grid(True)
plt.show()
