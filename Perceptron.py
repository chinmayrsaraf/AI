import matplotlib.pyplot as plt
x = [[1, -2, 1.5, 0], [1, -0.5, -2, -1.5], [0, 1, -1, 1.5]]
d = [1, -1, 1]
c_values = [1, 0.5, 1.5, 2, 2.5]
data = {}
def sgn(net):
    if net > 0:
        return 1
    else:
        return -1
for c in c_values:
    print(f"for c = {c}")
    w = [1, -1, 0, 0.5]
    e = 1
    counter = 0
    errors = []  
    while e != 0 :  
        counter += 1
        e = 0
        print("updated weights :")
        for i in range(3):
            sum = 0
            for j in range(4):
                net = w[j] * x[i][j]
                sum += net
            o = sgn(sum)
            e += (d[i] - o)
            for k in range(4):
                w[k] = w[k] + c * (d[i] - o) * x[i][k]
            
            print(w)
        print(f"error {e}")
        print(f"No of cycles {counter}")
        errors.append(e)
    data[c] = errors
    print("============")
for c, errors in data.items():
    plt.plot(range(len(errors )), errors, label=f'c = {c}')
plt.show()
