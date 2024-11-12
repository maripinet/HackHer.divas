document.addEventListener('DOMContentLoaded', () => {
    // BOTÃO VEJA MAIS - PARCEIRO
    const buttonSeeMore = document.getElementById('see-more');
    const hideDiv = document.getElementById('hide-content');

    const toggleSeeMore = () => {
        hideDiv.classList.toggle('show');
        const isShown = hideDiv.classList.contains('show');
        buttonSeeMore.innerHTML = isShown ? 'Ver menos' : 'Ver mais';
        buttonSeeMore.style.backgroundColor = isShown ? '#D62828' : '#FCBF49';
    };

    buttonSeeMore.addEventListener('click', toggleSeeMore);

    // MENU HAMBURGUER
    const hamburguer = document.querySelector('.hamburguer');
    const navMenu = document.querySelector('.nav-menu');

    const toggleMenu = () => {
        navMenu.classList.toggle('active');
    };

    hamburguer.addEventListener('click', toggleMenu);

    // LAMPADA - CTA
    const lamp = document.querySelector('.lamp');
    const containerLamp = document.querySelector('.cta');
    const textLamps = document.querySelectorAll('.txt-cta');
    const moon = document.querySelector('.switch');
    const moonImage = document.getElementById('moonImage');

    const toggleLamp = () => {
        containerLamp.classList.toggle('active');
        textLamps.forEach(textLamp => {
            textLamp.classList.toggle('active');
        });

        lamp.src = containerLamp.classList.contains('active') ? './assets/icons/dark-lamp.svg' : './assets/icons/light_lamp.svg';
    };

    lamp.addEventListener('click', toggleLamp);

    // DARK MODE
    const toggleDarkMode = () => {
        document.documentElement.classList.toggle('dark-mode');
        toggleLamp();
        toggleMoonImage();
    };

    moon.addEventListener('click', toggleDarkMode);

    // LUA - DARK/LIGHT MODE
    const toggleMoonImage = () => {
        moonImage.src = document.documentElement.classList.contains('dark-mode') ? './assets/icons/light-moon.svg' : './assets/icons/moon.svg';
    };

});
