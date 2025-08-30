from utils import read_im, save_im, normalize
import matplotlib.pyplot as plt
import numpy as np

im = read_im("images/duck.jpeg")

import numpy as np

def convolve_im(im, kernel):
    """ A function that convolves an image (RGB) with a given kernel.

    Args:
        im (np.array): Image array of shape [H, W, 3] (Height, Width, RGB channels)
        kernel (np.array): Convolution kernel array of shape [K, K]

    Returns:
        np.array: Convolved image of shape [H, W, 3]
    """
    
    # Ensure the image has 3 dimensions (H, W, 3)
    assert len(im.shape) == 3

    # Flip the kernel both horizontally and vertically
    flipped_kernel = np.flip(kernel)

    # Extract dimensions of the image and the kernel
    H, W, RGB = im.shape
    K_x, K_y = flipped_kernel.shape

    # Calculate the padding size for the kernel (assuming K_x == K_y for square kernels)
    padding_size = K_x // 2

    # Create a padded version of the image
    padded_im = np.zeros((H + 2 * padding_size, W + 2 * padding_size, RGB))
    padded_im[padding_size:H + padding_size, padding_size:W + padding_size, :] = im

    # Create an output image array to store the convolved results
    convolved_im = np.zeros_like(im)

    # Perform convolution for each pixel in the image
    for x in range(H):
        for y in range(W):
            for channel in range(RGB):
                # Extract the region of interest in the padded image for the current pixel
                region = padded_im[x:x + K_x, y:y + K_y, channel]
                
                # Perform element-wise multiplication between the region and the flipped kernel
                # Then sum up the results to get the convolved value for the current pixel
                convolved_im[x, y, channel] = np.sum(region * flipped_kernel)

    return convolved_im



# Sobel filter for edge detection
h_a = np.array([
    [-1, 0, 1],
    [-2, 0, 2],
    [-1, 0, 1]
])

# Read the image
im = read_im("images/duck.jpeg")



# Gaussian smoothing kernel
h_b = 1 / 256 * np.array([
    [1, 4, 6, 4, 1],
    [4, 16, 24, 16, 4],
    [6, 24, 36, 24, 6],
    [4, 16, 24, 16, 4],
    [1, 4, 6, 4, 1]
])


# Apply Sobel filter for edge detection
im_sobel = convolve_im(im, h_a)
save_im("results/duck_spatial_convolution_sobel.jpeg", im_sobel, cmap='gray')


# Apply Gaussian smoothing
im_smoothed = convolve_im(im, h_b)
save_im("results/duck_spatial_convolution_smooth.jpeg", im_smoothed, cmap='gray')