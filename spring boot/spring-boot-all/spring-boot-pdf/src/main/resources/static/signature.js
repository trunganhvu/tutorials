$(document).ready(function () {
    const canvas = document.createElement("canvas");
    const ctx = canvas.getContext("2d");
    const signaturePad = $("#signature-pad");
    let isDrawing = false;

    // Initialize canvas
    canvas.width = signaturePad.width();
    canvas.height = signaturePad.height();
    signaturePad.append(canvas);

    // Drawing events
    canvas.addEventListener("mousedown", (e) => {
        isDrawing = true;
        ctx.moveTo(e.offsetX, e.offsetY);
    });

    canvas.addEventListener("mousemove", (e) => {
        if (isDrawing) {
            ctx.lineTo(e.offsetX, e.offsetY);
            ctx.stroke();
        }
    });

    canvas.addEventListener("mouseup", () => {
        isDrawing = false;
        ctx.beginPath();
    });

    // Clear signature
    $("#clear-signature").click(function () {
        ctx.clearRect(0, 0, canvas.width, canvas.height);
    });

    // Submit signature
    $("#submit-signature").click(function () {
        const signatureData = canvas.toDataURL("image/png");
        console.log(signatureData)
        const payload = {
            userId: "12345", // Replace with dynamic user ID
            signature: signatureData
        };

        $.ajax({
            url: "https://your-api-endpoint.com/api/register",
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify(payload),
            success: function (response) {
                alert("Chữ ký của bạn đã được gửi thành công!");
            },
            error: function (xhr, status, error) {
                alert("Có lỗi xảy ra khi gửi chữ ký: " + error);
            }
        });
    });
});
