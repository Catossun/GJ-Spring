function editNote(index, originText) {
    let newText = prompt("修改備註欄位", originText);
    if (newText == null) return;
    document.getElementById("examNote").value = newText;
    document.getElementById("exam").action += index + "/note";
    document.getElementById("_method").value = "PUT";
    document.getElementById("exam").submit();
}

function updateExam(index) {
    document.getElementById("exam").action += index;
    document.getElementById("exam").submit();
}

function deleteExam(index) {
    document.getElementById("_method").value = "DELETE";
    updateExam(index);
}