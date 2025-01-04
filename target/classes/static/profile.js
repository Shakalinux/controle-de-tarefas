function alternarImagem() {
    var imagem = document.getElementById('imagemPrincipal');
    if (imagem.classList.contains('hidden')) {
        imagem.classList.remove('hidden');
        document.getElementById('toggleImageBtn').textContent = 'Recolher';
    } else {
        imagem.classList.add('hidden');
        document.getElementById('toggleImageBtn').textContent = 'Expandir';
    }
}
