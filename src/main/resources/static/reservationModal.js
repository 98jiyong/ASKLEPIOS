document.getElementById('openModal').addEventListener('click', () => {
  fetch('openModal.html')
      .then(response => response.text())
      .then(html => {
        document.getElementById('modalContainer').innerHTML = html;
        document.querySelector('.modal').classList.add('active');
      });
});