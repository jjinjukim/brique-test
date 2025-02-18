// 함수: 입력 테이블에서 각 월의 기온 및 습도 데이터를 추출
function getInputData() {
    const temps = [];
    const hums = [];
    document.querySelectorAll('.temperature').forEach(input => {
        temps.push(parseFloat(input.value) || 0);
    });
    document.querySelectorAll('.humidity').forEach(input => {
        hums.push(parseFloat(input.value) || 0);
    });
    return { temps, hums };
}

// x축 레이블: 12개월
const labels = ["1월", "2월", "3월", "4월", "5월", "6월",
    "7월", "8월", "9월", "10월", "11월", "12월"];

// 캔버스 객체와 Chart.js 인스턴스 생성 (차트 객체를 전역 변수로 선언)
const ctx = document.getElementById('myChart').getContext('2d');
const myChart = new Chart(ctx, {
    type: 'line',
    data: {
        labels: labels,
        datasets: [{
            label: '평균 기온',
            yAxisID: 'y-left',
            data: [], // 초기 데이터
            borderColor: 'rgba(255, 99, 132, 1)',
            backgroundColor: 'rgba(255, 99, 132, 0.2)',
            borderWidth: 2,
            pointRadius: 3,
            fill: false,
            tension: 0.1
        },{
            label: '평균 습도',
            yAxisID: 'y-right',
            data: [], // 초기 데이터
            borderColor: 'rgba(54, 162, 235, 1)',
            backgroundColor: 'rgba(54, 162, 235, 0.2)',
            borderWidth: 2,
            pointRadius: 3,
            fill: false,
            tension: 0.1
        }]
    },
    options: {
        responsive: true,
        scales: {
            x: {
                grid: { display: false }
            },
            'y-left': {
                type: 'linear',
                position: 'left',
                title: { display: true, text: '평균 기온 (°C)' },
                ticks: { min: 0, max: 40, stepSize: 10 },
                grid: { display: true }
            },
            'y-right': {
                type: 'linear',
                position: 'right',
                title: { display: true, text: '평균 습도 (%)' },
                ticks: { min: 0, max: 100, stepSize: 10 },
                grid: { display: false }
            }
        },
        plugins: {
            legend: { position: 'top' }
        }
    }
});

// 그래프 업데이트 함수: 입력 테이블의 값을 읽어와 차트 데이터를 업데이트
function updateChart() {
    const data = getInputData();
    myChart.data.datasets[0].data = data.temps;
    myChart.data.datasets[1].data = data.hums;
    myChart.update();
}

// 이벤트 리스너: 모든 입력 필드 변경 시 updateChart() 호출
document.addEventListener("DOMContentLoaded", function() {
    document.querySelectorAll('.temperature, .humidity').forEach(input => {
        input.addEventListener('input', updateChart);
    });
    // "Random" 버튼 클릭 이벤트 처리
    document.getElementById('randomButton').addEventListener('click', () => {
        document.querySelectorAll('.temperature').forEach(input => {
            input.value = (Math.random() * 50 - 10).toFixed(1);
        });
        document.querySelectorAll('.humidity').forEach(input => {
            input.value = (Math.random() * 100).toFixed(1);
        });
        updateChart();
    });
    // 초기 업데이트 호출
    updateChart();
});