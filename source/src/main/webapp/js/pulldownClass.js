const select = document.getElementById('select_class');
  const output = document.getElementById('output');

  select.addEventListener('change', () => {
    const selectClass = select.value;
    if (selectClass) {
      output.textContent = selectClass;
    } else {
      output.textContent = '';
    }
  });
  
 document.getElementById('form_month').select.onchange = function() {
	location.href = document.getElementById('form_month').select.value;
}