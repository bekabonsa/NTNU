from utils import read_im, save_im
import matplotlib.pyplot as plt


im = read_im("results/duck_greyscale.jpeg")



def inverse_greyscale(im):
    """ Converts an RGB image to greyscale using NumPy.

    Args:
        im (list): A 3D list or NumPy array of shape [H, W, 3], where each element is a pixel with RGB values.

    Returns:
        np.array: A 2D NumPy array of shape [H, W] representing the grayscale image.
    """

  
    return 1-im


im_inverse_greyscale = inverse_greyscale(im)

plt.imshow(im_inverse_greyscale, cmap='gray')
save_im("results/duck_inverse_greyscale.jpeg", im_inverse_greyscale, cmap='gray')