function showLoginForm(event) {
    event.preventDefault();

    document.getElementById('loginForm').style.display = 'block';

    this.submit();
}