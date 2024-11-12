function SendEmail() {
    const name = document.getElementById("name").value;
    const email = document.getElementById("email").value;
    const message = document.getElementById("message").value;

    Email.send({
        SecureToken: 'ed5407c6-f07c-41b1-ae76-fba516d813cf',
        To: 'aneskakss@gmail.com',
        From: "aneskakss@gmail.com",
        Subject: "Form ✨⚠️:",
        Body: `
            Name: ${name} <br>
            Email: ${email} <br>
            Message: ${message}
        `
    }).then((message) => {
        const popup = document.getElementById('popup');
        popup.style.display = 'block';
        setTimeout(() => {
            popup.style.display = 'none';
        }, 2000);
    }).catch((error) => {
        console.error("Erro ao enviar email: ", error);
        alert("Falha ao enviar o email. Tente novamente mais tarde.");
    });
}