const select = document.getElementById('month');
  const output = document.getElementById('output');

  select.addEventListener('change', () => {
    const month = select.value;
    if (month) {
      output.textContent = month + '月の成績';
    } else {
      output.textContent = '';
    }
  });
  
 document.getElementById('form').select.onchange = function() {
	location.href = document.getElementById('form').select.value;
}