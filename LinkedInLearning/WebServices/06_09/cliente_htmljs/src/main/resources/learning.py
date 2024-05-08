# This is a basic Python program that calculates the area of a rectangle.

# Define variables for the length and width of the rectangle
length = 5
width = 3

# Calculate the area of the rectangle
area = length * width

# Print the result
print("The area of the rectangle is:", area)

# Function to calculate area of rectangle
def calculate_area(length, width):
    return length * width

# Function to calculate perimeter of rectangle
def calculate_perimeter(length, width):
    return 2 * (length + width)

# Function to handle invalid inputs
def handle_invalid_input():
    print("Invalid input. Please enter valid numbers.")

# Main function
def main():
    try:
        # Input length and width from user
        length = float(input("Enter the length of the rectangle: "))
        width = float(input("Enter the width of the rectangle: "))

        # Check if inputs are valid (positive numbers)
        if length <= 0 or width <= 0:
            raise ValueError

        # Calculate area and perimeter
        area = calculate_area(length, width)
        perimeter = calculate_perimeter(length, width)

        # Print results
        print("The area of the rectangle is:", area)
        print("The perimeter of the rectangle is:", perimeter)

    except ValueError:
        handle_invalid_input()

# Execute the main function
if __name__ == "__main__":
    main()


