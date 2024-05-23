let node_id;
let runData;

 const postNodeAndRun = async () => {
  const url = 'http://localhost:8000/nodes/node'; // Base URL for posting a new node
  const requestBody = {
    "config": {
      "inputs": [
        {
          "data_point_id": "c914fad0-4428-4268-9e33-1c21e345f4b9",
          "data_point_values": [],
          "variable": "A"
        },

        {
          "data_point_id": "40dbf6bd-0719-4b0b-9f94-1e2787bd05cd",
          "data_point_values": [],
          "variable": "B"
        }
      ],
      "formula_settings": {
        "formula": "Average",
        "approximation_settings": {
          "interpolation_method": "Average in interval",
          "interval_seconds": 1,
          "average_interval": "1s"
        },
        "expression": "A",
        "output_format": "Time series"
      },
      "input_settings": {
        "time_reference_mode": "Start end",
        "start_event_time": "2024-04-24T11:30:16.340Z",
        "end_event_time": "2024-04-24T11:30:55.340Z",
        "past_timeframe_seconds": 5,
        "event_time": "2024-04-25T13:34:16.340Z"
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



postNodeAndRun().then(r => r);



document.addEventListener('DOMContentLoaded', () => {
  const drawChartBtn = document.getElementById('drawChartBtn');

  console.log(new Date("2024-04-24T13:30:16.340000+02:00")); // Test date parsing directly

  drawChartBtn.addEventListener('click', () => {
    let output_data =  [
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

    let input_data_1 =  [
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


    output_data = runData.result.config.output;
    input_data_1 = runData.result.config.inputs[0].data_point_values;
    let input_data_2 = runData.result.config.inputs[1].data_point_values;
    console.log(input_data_1)

    const parseData = (data) => data.map(item => ({
      x: new Date(item.event_time),
      y: item.value
    }));

    console.log(new Date(output_data[0].event_time)); // Check if dates are parsed correctly


    const outputDataPoints = parseData(output_data);
    const inputDataPoints = parseData(input_data_1);
    const inputDataPoints_2 = parseData(input_data_2);


    const data = {
      datasets: [{
        label: 'Output Data',
        backgroundColor: transparentize('#FF6384', 0.5), // Using the custom transparentize function
        borderColor: '#FF6384',
        fill: false,
        data: outputDataPoints,
      }, {
        label: 'Input Data',
        backgroundColor: transparentize('#36A2EB', 0.5),
        borderColor: '#36A2EB',
        fill: false,
        data: inputDataPoints,
      },
        {
          label: 'Input Data 2',
          backgroundColor: transparentize('#34e720', 0.5),
          borderColor: '#49e312',
          fill: false,
          data: inputDataPoints_2,
        },
      ]
    };




    const ctx = document.getElementById('myChart').getContext('2d');
    const config = {
      type: 'line',
      data: data,
      options: {
        scales: {
          x: {
            type: 'time',
            time: {
              unit: 'second',
              tooltipFormat: 'MMM d, h:mm:ss a', // changed 'D' to 'd',
              displayFormats: {
                second: 'MMM d, h:mm:ss a' // changed 'D' to 'd'
              }
            },
            title: {
              display: true,
              text: 'Event Time'
            }
          }
          ,
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
            text: 'Comparison of Input and Output Data Over Time'
          }
        }
      }
    };

    // Initializing the chart
    if (window.myChart instanceof Chart) {
      window.myChart.destroy();
    }
    window.myChart = new Chart(ctx, config);
  });
});


function transparentize(color, opacity) {
  let alpha = 1 - opacity;
  return `rgba(${parseInt(color.slice(1, 3), 16)}, ${parseInt(color.slice(3, 5), 16)}, ${parseInt(color.slice(5, 7), 16)}, ${alpha})`;
}

function newDate(days) {
  let date = new Date();
  date.setDate(date.getDate() + days);
  return date;
}

function newDateString(days) {
  return newDate(days).toISOString();
}

