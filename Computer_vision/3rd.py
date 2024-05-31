import cv2
import numpy as np
import matplotlib.pyplot as plt

# Load your images A and B (replace 'image_A.jpg' and 'image_B.jpg' with your image file paths)
image_A = cv2.imread('img2.jpeg', cv2.IMREAD_GRAYSCALE)
image_B = cv2.imread('img3.jpeg', cv2.IMREAD_GRAYSCALE)

# Compute the Fourier Transform of images A and B
ft_A = np.fft.fft2(image_A)
ft_B = np.fft.fft2(image_B)

# Calculate the magnitude and phase of the Fourier Transform
magnitude_A = np.abs(ft_A)
phase_A = np.angle(ft_A)
magnitude_B = np.abs(ft_B)
phase_B = np.angle(ft_B)

# Scale up phase images to visualize content
scaled_phase_A = (phase_A - phase_A.min()) / (phase_A.max() - phase_A.min()) * 255
scaled_phase_B = (phase_B - phase_B.min()) / (phase_B.max() - phase_B.min()) * 255

# Create magnitude-only and phase-only reconstructions
magnitude_only_A = np.fft.ifft2(magnitude_A * np.exp(1j * np.zeros_like(phase_A))).real
phase_only_A = np.fft.ifft2(np.ones_like(magnitude_A) * np.exp(1j * scaled_phase_A)).real
magnitude_only_B = np.fft.ifft2(magnitude_B * np.exp(1j * np.zeros_like(phase_B))).real
phase_only_B = np.fft.ifft2(np.ones_like(magnitude_B) * np.exp(1j * scaled_phase_B)).real

# Display the results
plt.figure(figsize=(10, 6))

plt.subplot(2, 3, 1)
plt.imshow(image_A, cmap='gray')
plt.title('Image A')

plt.subplot(2, 3, 2)
plt.imshow(magnitude_only_A, cmap='gray')
plt.title('Magnitude-only Reconstruction A')

plt.subplot(2, 3, 3)
plt.imshow(phase_only_A, cmap='gray')
plt.title('Phase-only Reconstruction A')

plt.subplot(2, 3, 4)
plt.imshow(image_B, cmap='gray')
plt.title('Image B')

plt.subplot(2, 3, 5)
plt.imshow(magnitude_only_B, cmap='gray')
plt.title('Magnitude-only Reconstruction B')

plt.subplot(2, 3, 6)
plt.imshow(phase_only_B, cmap='gray')
plt.title('Phase-only Reconstruction B')

plt.tight_layout()
plt.show()
