import numpy as np

import matplotlib.pyplot as plt

def assignMembership(value):
    if value == 0:
        return 1
    elif value < 1 and value > 0:
        return 1 - value
    else :
        return 0



def distance(x, y):
    return np.sqrt((1 - x)**2 + (1 - y)**2)

def membership():
    values = []
    x = [0.1, 0.2, 0.3, 0.4, 0.5]
    y = [0.1, 0.1, 0.3, 0.3, 0.1]

    for i in range(len(x)):
        for j in range(len(y)):
            values.append(assignMembership(distance(x[i], y[j])))
    

    print("The memebership values are :")
    for i in values:
        print(i)
    
    plt.plot(values, marker = 'o')
    plt.markersize=10
    plt.show()

membership()


# ------------------------------------------------------------------------------------------

import math
import numpy as np
import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d import Axes3D

x1 = 0.5
y1 = 0.5

num_points = int(input("Enter the number of points: "))

x_values = []
y_values = []
j_values = []

for _ in range(num_points):
    x2 = float(input("Enter x2: "))
    y2 = float(input("Enter y2: "))

    distance = math.sqrt((x2 - x1) ** 2 + (y2 - y1) ** 2)

    if distance == 0:
        j = 1
        
    elif distance > 1:
        j = 0
    else:
        j = 1.0 - distance
    
    print(j)
    x_values.append(x2)
    y_values.append(y2)
    j_values.append(j)

fig = plt.figure()
ax = fig.add_subplot(111, projection='3d')
ax.scatter(x_values, y_values, j_values, c=j_values, cmap='viridis')
ax.set_xlabel('X')
ax.set_ylabel('Y')
ax.set_zlabel('J Value')

plt.show()

# ----------------------------------------------------------------------------------------------

import math
import numpy as np
import matplotlib.pyplot as plt

x1 = 0.5
y1 = 0.5

num_points = int(input("Enter the number of points: "))

x_values = []
y_values = []
j_values = []

for _ in range(num_points):
    x2 = float(input("Enter x2: "))
    y2 = float(input("Enter y2: "))

    # Corrected distance calculation
    distance = math.sqrt((x2 - x1)**2 + (y2 - y1)**2)

    if distance == 0:
        j = 1
    elif distance > 1:
        j = 0
    else:
        j = 1.0 - distance

    print(j)
    x_values.append(x2)
    y_values.append(y2)
    j_values.append(j)

plt.scatter(x_values, y_values, c=j_values, cmap='viridis')
plt.xlabel('X')
plt.ylabel('Y')
plt.title('J Values for Given Points')
plt.colorbar(label='J Value')
plt.show()


# ------------------------------------------------------------------------------------------

import numpy as np
import matplotlib.pyplot as plt

# Define the membership function
def calculate_membership(x, x1, gamma):
    r = 1
    distance = np.abs(x - x1)

    y_membership = r * distance
    if(y_membership<0):
        membership_value=0
    elif(y_membership<1 and y_membership>0):
        membership_value = 1 - y_membership
    else:
        membership_value=1
    return membership_value
    return membership_value

x1 = 0.5  # Center
gamma = 4  # Gamma value

# X-coordinates to calculate membership values for
x_coordinates = [1, 2, 3, 4, 5]

# Calculate membership values
membership_values = [calculate_membership(x, x1, gamma) for x in x_coordinates]

# Plotting the membership values for the x-coordinates
plt.plot(x_coordinates, membership_values, marker='o')
plt.xlabel('X Coordinate')
plt.ylabel('Membership Value')
plt.title(f'Membership Values for Gamma={gamma}')
plt.grid(True)
plt.show()
    





