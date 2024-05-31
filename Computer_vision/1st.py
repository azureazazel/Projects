import cv2
import numpy as np


# Load the large image (replace 'large_image_path' with the path to your image)
large_image_path = 'img2.jpeg'
large_image = cv2.imread(large_image_path)

# Define the scaling factor (e.g., 0.5 for half size, 2.0 for double size)
scaling_factor = 0.5

# Specify the interpolation method (e.g., cv2.INTER_LINEAR for bilinear interpolation)
interpolation_method = cv2.INTER_LINEAR

# Resize the large image with interpolation
rescaled_image = cv2.resize(large_image, None, fx=scaling_factor, fy=scaling_factor, interpolation=interpolation_method)

# Display the original and rescaled images
cv2.imshow("Original Image", large_image)
cv2.imshow("Rescaled Image", rescaled_image)
cv2.waitKey(0)
cv2.destroyAllWindows()

# Save the rescaled image (optional)
cv2.imwrite('rescaled_image.jpg', rescaled_image)


# Create a black square on a white background
image_size = (200, 200)
# Display the original square image
cv2.imshow("Original Square", rescaled_image)
cv2.waitKey(0)

# Define the shear matrix (S)
shear_matrix = np.array([[25, 25, 0],
                         [0, 30, 0],
                         [0, 0, 30]], dtype=float)

# Apply shear transformation
sheared_square = cv2.warpPerspective(rescaled_image, shear_matrix, (rescaled_image.shape[1], rescaled_image.shape[0]), flags=cv2.INTER_LINEAR)

# Display the sheared square image
cv2.imshow("Sheared Square", sheared_square)
cv2.waitKey(0)

# Define the rotation matrix (R)
rotation_angle_degrees = -15  # The calculated rotation angle
rotation_matrix = cv2.getRotationMatrix2D((image_size[1] / 2, image_size[0] / 2), rotation_angle_degrees, 1)

# Apply rotation transformation
rotated_square = cv2.warpAffine(sheared_square, rotation_matrix, (rescaled_image.shape[1], rescaled_image.shape[0]), flags=cv2.INTER_LINEAR)

# Display the rotated square image
cv2.imshow("Rotated Square", rotated_square)
cv2.waitKey(0)

# Define the scaling matrix (M)
scaling_matrix = np.array([[1000, 0, 800],
                           [0, 2000, 0],
                           [0, 0, 2000]], dtype=float)

# Apply scaling transformation
scaled_square = cv2.warpPerspective(rotated_square, scaling_matrix, (rescaled_image.shape[1], rescaled_image.shape[0]), flags=cv2.INTER_LINEAR)

# Display the scaled square image
cv2.imshow("Scaled Square", scaled_square)
cv2.waitKey(0)

# Define the translation matrix (T)
translation_matrix = np.array([[1, 0, 4],
                              [0, 1, 0],
                              [0, 0, 1]], dtype=float)

# Apply translation transformation
translated_square = cv2.warpPerspective(scaled_square, translation_matrix, (rescaled_image.shape[1], rescaled_image.shape[0]), flags=cv2.INTER_LINEAR)

# Display the translated square image
cv2.imshow("Translated Square", translated_square)
cv2.waitKey(0)

# Compose all transformations into a single affine transformation
composed_matrix = np.matmul(translation_matrix, np.matmul(scaling_matrix, np.matmul(rotation_matrix, shear_matrix)))

# Apply the composed transformation
composed_square = cv2.warpPerspective(rescaled_image, composed_matrix, (rescaled_image.shape[1], rescaled_image.shape[0]), flags=cv2.INTER_LINEAR)

# Display the final composed image
cv2.imshow("Composed Square", composed_square)
cv2.waitKey(0)
cv2.destroyAllWindows()
