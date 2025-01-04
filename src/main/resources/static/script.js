document.getElementById("menu-toggle").addEventListener("click", function() {
    document.getElementById("wrapper").classList.toggle("toggled");
});

document.addEventListener('DOMContentLoaded', function () {
    var tasks =  0;
    if (tasks == 0) {
        var noTaskModal = new bootstrap.Modal(document.getElementById('noTaskModal'));
        noTaskModal.show();
    }
});
