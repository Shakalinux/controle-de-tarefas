document.addEventListener("DOMContentLoaded", () => {
    const form = document.querySelector('#reminderModal form');
    const submitButton = form.querySelector('button[type="submit"]');

    form.addEventListener("submit", (event) => {
        event.preventDefault();
        submitButton.disabled = true;
        submitButton.textContent = "Estamos agendando seu lembrete! Aguarde...";
        setTimeout(() => {
            form.submit();
            alert("Lembrete agendado com sucesso!");
        }, 3000);
    });
});
