// Initialize WebTorrent
const client = new WebTorrent();

const searchButton = document.getElementById('search-button');
const movieTitleInput = document.getElementById('movie-title');
const searchResults = document.getElementById('search-results');
const videoPlayer = document.getElementById('video-player');

// YTS API Base URL
const YTS_API_URL = "https://yts.mx/api/v2/list_movies.json";

// Function to fetch movies from YTS API
async function fetchMovies(title) {
    const url = `${YTS_API_URL}?query_term=${encodeURIComponent(title)}&limit=5`;
    try {
        const response = await fetch(url);
        const data = await response.json();

        if (data.status === "ok" && data.data.movies) {
            return data.data.movies;
        } else {
            return [];
        }
    } catch (error) {
        console.error("Error fetching movies:", error);
        return [];
    }
}

// Function to render search results
function renderSearchResults(movies) {
    searchResults.innerHTML = "";
    if (movies.length === 0) {
        searchResults.innerHTML = "<p>No movies found.</p>";
        return;
    }

    movies.forEach((movie) => {
        const movieCard = document.createElement("div");
        movieCard.className = "movie-card";

        movieCard.innerHTML = `
            <h3>${movie.title}</h3>
            <p>Year: ${movie.year}</p>
            <p>Rating: ${movie.rating}</p>
            <button data-magnet="${movie.torrents[0].url}" class="stream-button">Stream</button>
        `;
        searchResults.appendChild(movieCard);
    });

    // Add event listeners to stream buttons
    document.querySelectorAll(".stream-button").forEach((button) => {
        button.addEventListener("click", (e) => {
            const magnetURI = e.target.getAttribute("data-magnet");
            if (magnetURI) {
                startStreaming(magnetURI);
            }
        });
    });
}

// Function to start streaming a torrent
function startStreaming(magnetURI) {
    client.add(magnetURI, (torrent) => {
        console.log(`Torrent metadata: ${torrent.name}`);

        const file = torrent.files.find((file) => file.name.endsWith(".mkv"));
        if (!file) {
            alert("No MKV file found in this torrent.");
            return;
        }

        file.renderTo(videoPlayer, (err) => {
            if (err) {
                console.error("Error rendering video:", err);
            } else {
                console.log("Streaming started.");
            }
        });
    });
}

// Event listener for the search button
searchButton.addEventListener("click", async () => {
    const title = movieTitleInput.value.trim();
    if (!title) {
        alert("Please enter a movie title.");
        return;
    }

    const movies = await fetchMovies(title);
    renderSearchResults(movies);
});



const streamButton = document.getElementById('stream-button');
const magnetInput = document.getElementById('magnet-link');
const vlcPlayerContainer = document.getElementById('vlc-player');

streamButton.addEventListener('click', () => {
    const magnetURI = magnetInput.value.trim();

    if (!magnetURI) {
        alert('Please provide a valid magnet link.');
        return;
    }

    // Start torrent download
    client.add(magnetURI, (torrent) => {
        console.log(`Torrent metadata: ${torrent.name}`);

        // Find the first .mkv file
        const file = torrent.files.find((file) => file.name.endsWith('.mkv'));
        if (!file) {
            alert('No MKV file found in this torrent.');
            return;
        }

        // Create VLC.js instance for playback
        file.getBlob((err, blob) => {
            if (err) {
                console.error("Error getting file blob:", err);
                return;
            }

            const blobURL = URL.createObjectURL(blob);

            const vlc = VLC({
                url: blobURL, // Set the file URL for playback
                container: vlcPlayerContainer, // Render VLC player in this container
                width: 640,
                height: 360,
                autoplay: true,
            });

            vlc.play();
        });
    });
});
