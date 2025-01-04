document.addEventListener('DOMContentLoaded', () => {
    const modalElement = document.getElementById('modal');
    if (modalElement) {
        const myModal = new bootstrap.Modal(modalElement);
        myModal.show();
    }
});