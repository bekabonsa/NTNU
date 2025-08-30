from utils import read_im, save_im
import matplotlib.pyplot as plt
import numpy as np

im = read_im("images/duck.jpeg")



def greyscale(im):
    """ Converts an RGB image to greyscale using NumPy.

    Args:
        im (list): A 3D list or NumPy array of shape [H, W, 3], where each element is a pixel with RGB values.

    Returns:
        np.array: A 2D NumPy array of shape [H, W] representing the grayscale image.
    """

    im_array = np.array(im)
    
    # Apply the grayscale conversion using the weighted sum formula
    grayscale_image = 0.2126 * im_array[..., 0] + 0.7152 * im_array[..., 1] + 0.0722 * im_array[..., 2]
    
    return grayscale_image


im_greyscale = greyscale(im)

plt.imshow(im_greyscale, cmap='gray')
save_im("results/duck_greyscale.jpeg", im_greyscale, cmap='gray')