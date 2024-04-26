let node_id;
let runData;

/** const postNodeAndRun = async () => {
  const url = 'http://localhost:8000/nodes/node'; // Base URL for posting a new node
  const requestBody = {
    "config": {
      "inputs": [
        {
          "data_point_id": "c914fad0-4428-4268-9e33-1c21e345f4b9",
          "data_point_values": [],
          "variable": "A"
        }
      ],
      "formula_settings": {
        "formula": "Sum",
        "approximation_settings": {
          "series_approximation_method": "Linear interpolation",
          "interpolation_seconds": 1
        },
        "expression": "A",
        "output_format": "Time series"
      },
      "input_settings": {
        "time_reference_mode": "Start end",
        "start_event_time": "2024-04-24T11:30:16.340Z",
        "end_event_time": "2024-04-24T11:30:55.340Z",
        "past_timeframe_seconds": 5,
        "event_time": "2024-04-24T13:34:16.340Z"
      },
      "output": 0
    },
    "continuous_trigger": {
      "trigger_type": "Interval based",
      "interval_seconds": 19,
      "time": "13:34:16.340Z"
    }
  };

  try {
    const postResponse = await fetch(url, {
      method: 'POST',
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify(requestBody)
    });

    if (!postResponse.ok) {
      throw new Error(`HTTP error! status: ${postResponse.statusText}`);
    }

    const postData = await postResponse.json();
    console.log('Node added successfully:', postData);

    if (postData && postData.Node && /^[0-9a-fA-F-]{36}$/.test(postData.Node.node_id)) {
      node_id = postData.Node.node_id;
      console.log('Node ID:', node_id);
      const runUrl = `http://localhost:8000/nodes/${node_id}/run`;
      const runResponse = await fetch(runUrl, {
        method: 'POST',
        headers: {'Content-Type': 'application/json'}
      });

      if (!runResponse.ok) {
        const errorText = await runResponse.text();  // Get full response text
        console.error(`HTTP error! status: ${runResponse.status}`, errorText);
        throw new Error(`HTTP error! status: ${runResponse.status}`);
      }

      runData = await runResponse.json();
      console.log('Run node result:', runData);

    }
  } catch (error) {
    console.error('Error:', error);
  }
};



postNodeAndRun();
 **/


document.addEventListener('DOMContentLoaded', () => {
  const drawChartBtn = document.getElementById('drawChartBtn');

  drawChartBtn.addEventListener('click', () => {
    const output_data =  [
      {
        "value": 18.931459114927343,
        "event_time": "2024-04-24T13:30:16.340000+02:00"
      },
      {
        "value": 18.923232035053555,
        "event_time": "2024-04-24T13:30:36.340000+02:00"
      },
      {
        "value": 18.92051983842011,
        "event_time": "2024-04-24T13:30:56.340000+02:00"
      },
      {
        "value": 18.88589665513264,
        "event_time": "2024-04-24T13:31:16.340000+02:00"
      }
    ];

    const input_data =  [
      {
        "value": 18.9349,
        "event_time": "2024-04-24T13:30:15.887000+02:00"
      },
      {
        "value": 18.9234,
        "event_time": "2024-04-24T13:30:17.401000+02:00"
      },
      {
        "value": 18.9349,
        "event_time": "2024-04-24T13:30:23.797000+02:00"
      },
      {
        "value": 18.9234,
        "event_time": "2024-04-24T13:30:25.467000+02:00"
      },
      {
        "value": 18.9119,
        "event_time": "2024-04-24T13:30:31.105000+02:00"
      },
      {
        "value": 18.9234,
        "event_time": "2024-04-24T13:30:36.280000+02:00"
      },
      {
        "value": 18.9119,
        "event_time": "2024-04-24T13:30:40.388000+02:00"
      },
      {
        "value": 18.9004,
        "event_time": "2024-04-24T13:30:41.636000+02:00"
      },
      {
        "value": 18.9119,
        "event_time": "2024-04-24T13:30:44.144000+02:00"
      },
      {
        "value": 18.9004,
        "event_time": "2024-04-24T13:30:46.370000+02:00"
      },
      {
        "value": 18.9234,
        "event_time": "2024-04-24T13:30:55.503000+02:00"
      },
      {
        "value": 18.9119,
        "event_time": "2024-04-24T13:30:58.845000+02:00"
      },
      {
        "value": 18.9004,
        "event_time": "2024-04-24T13:31:05.806000+02:00"
      },
      {
        "value": 18.9119,
        "event_time": "2024-04-24T13:31:08.276000+02:00"
      },
      {
        "value": 18.9004,
        "event_time": "2024-04-24T13:31:09.816000+02:00"
      },
      {
        "value": 18.9119,
        "event_time": "2024-04-24T13:31:12.687000+02:00"
      },
      {
        "value": 18.9004,
        "event_time": "2024-04-24T13:31:14.172000+02:00"
      },
      {
        "value": 18.8888,
        "event_time": "2024-04-24T13:31:15.906000+02:00"
      },
      {
        "value": 18.88589665513264,
        "event_time": "2024-04-24T13:31:16.340000+02:00"
      }
    ];

    // Step 1: Merge and Sort Timestamps
    const allTimestamps = [...output_data, ...input_data]
        .map(data => data.event_time)
        .filter((value, index, self) => self.indexOf(value) === index)
        .sort((a, b) => new Date(a) - new Date(b));

    // Step 2: Prepare Data for Plotting
    const outputValues = allTimestamps.map(timestamp => {
      const found = output_data.find(data => data.event_time === timestamp);
      return found ? found.value : null;
    });

    const inputValues = allTimestamps.map(timestamp => {
      const found = input_data.find(data => data.event_time === timestamp);
      return found ? found.value : null;
    });

    const labels = allTimestamps.map(timestamp => new Date(timestamp).toLocaleString());

    const processedOutputValues = [];
    let lastOutputValue = null;
    allTimestamps.forEach(timestamp => {
      const found = output_data.find(data => data.event_time === timestamp);
      if (found) {
        lastOutputValue = found.value;
      }
      processedOutputValues.push(lastOutputValue);
    });

    // Process the input_data similarly
    const processedInputValues = [];
    let lastInputValue = null;
    allTimestamps.forEach(timestamp => {
      const found = input_data.find(data => data.event_time === timestamp);
      if (found) {
        lastInputValue = found.value;
      }
      processedInputValues.push(lastInputValue);
    });



    // Step 3: Plot Using Chart.js
    const ctx = document.getElementById('myChart').getContext('2d');
    const config = {
      type: 'line',
      data: {
        labels: labels,
        datasets: [
      {
        label: 'Output Data',
        data: processedOutputValues,
          backgroundColor: 'rgba(0, 123, 255, 0.5)',
          borderColor: 'rgba(0, 123, 255, 1)',
          borderWidth: 2,
          pointRadius: 3,
          pointHoverRadius: 6,
          fill: false
        }, {
            label: 'Input Data',
            data: processedInputValues,
          backgroundColor: 'rgba(255, 99, 132, 0.5)',
          borderColor: 'rgba(255, 99, 132, 1)',
          borderWidth: 2,
          pointRadius: 3,
          pointHoverRadius: 6,
          fill: false
        }]
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        scales: {
          x: {
            type: 'time',
            time: {
              unit: 'second',
              tooltipFormat: 'MMM D, h:mm:ss a',
              displayFormats: {
                second: 'h:mm:ss a'
              }
            },
            title: {
              display: true,
              text: 'Event Time'
            }
          },
          y: {
            beginAtZero: false,
            title: {
              display: true,
              text: 'Value'
            }
          }
        },
        plugins: {
          legend: {
            position: 'top',
          },
          title: {
            display: true,
            text: 'Data Points Over Time'
          }
        },
        interaction: {
          intersect: false,
          mode: 'index',
        }
      }
    };

    if (window.myChart instanceof Chart) {
      window.myChart.destroy();
    }

    window.myChart = new Chart(ctx, config);
  });
});