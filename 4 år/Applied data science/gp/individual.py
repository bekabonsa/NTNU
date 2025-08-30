import matplotlib.pyplot as plt
import matplotlib.patches as mpatches

# Create figure and axis
fig, ax = plt.subplots(figsize=(12, 8))

# Add components as boxes in the diagram
components = [
    {"name": "Data Ingestion Layer", "x": 0.05, "y": 0.8, "width": 0.3, "height": 0.1},
    {"name": "Model Training & Evaluation", "x": 0.4, "y": 0.8, "width": 0.3, "height": 0.1},
    {"name": "Prediction & Inference Layer", "x": 0.75, "y": 0.8, "width": 0.2, "height": 0.1},
    {"name": "Recommendation System", "x": 0.4, "y": 0.6, "width": 0.3, "height": 0.1},
    {"name": "Human-in-the-Loop Feedback", "x": 0.05, "y": 0.4, "width": 0.3, "height": 0.1},
    {"name": "User Interface (UI)", "x": 0.75, "y": 0.6, "width": 0.2, "height": 0.1},
    {"name": "Data Security & Privacy", "x": 0.05, "y": 0.2, "width": 0.3, "height": 0.1},
    {"name": "Monitoring & Maintenance", "x": 0.4, "y": 0.2, "width": 0.3, "height": 0.1}
]

# Draw the boxes
for component in components:
    ax.add_patch(mpatches.FancyBboxPatch(
        (component["x"], component["y"]),
        component["width"],
        component["height"],
        boxstyle="round,pad=0.1",
        edgecolor="black",
        facecolor="#E6E6FA"
    ))
    ax.text(
        component["x"] + component["width"]/2,
        component["y"] + component["height"]/2,
        component["name"],
        horizontalalignment='center',
        verticalalignment='center',
        fontsize=10,
        weight='bold'
    )

# Draw arrows to show flow between components
arrows = [
    {"start": (0.35, 0.85), "end": (0.4, 0.85)},  # From Data Ingestion Layer to Model Training & Evaluation
    {"start": (0.7, 0.85), "end": (0.75, 0.85)},  # From Model Training to Prediction & Inference Layer
    {"start": (0.55, 0.75), "end": (0.55, 0.65)}, # From Model Training to Recommendation System
    {"start": (0.35, 0.65), "end": (0.05, 0.45)}, # From Recommendation System to Human-in-the-loop
    {"start": (0.55, 0.65), "end": (0.75, 0.65)}, # From Recommendation System to UI
    {"start": (0.15, 0.35), "end": (0.15, 0.25)}, # From Human-in-the-loop to Data Security
    {"start": (0.55, 0.25), "end": (0.55, 0.35)}, # From Monitoring to Recommendation
    {"start": (0.35, 0.25), "end": (0.05, 0.25)}  # From Monitoring to Data Security
]

# Draw the arrows
for arrow in arrows:
    ax.annotate(
        '',
        xy=arrow["end"],
        xytext=arrow["start"],
        arrowprops=dict(facecolor='black', shrink=0.05)
    )

# Hide axis
ax.set_axis_off()

# Show diagram
plt.show()
