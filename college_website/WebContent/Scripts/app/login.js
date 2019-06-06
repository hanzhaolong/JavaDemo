if (JParticle) {
    new JParticle();
    $(".bg").jParticle({
        background: "#339999",
        color: "#FFFFFF"
    });
}

if (CompatiblePlaceHolder)
    CompatiblePlaceHolder();

$(".login-btn").click(function () {
    location.href = "/index.html";
});