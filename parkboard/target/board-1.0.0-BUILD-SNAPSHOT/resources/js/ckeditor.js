ClassicEditor
.create(document.querySelector('#board_content'))
.then(editor => {
console.log(editor);
})
.catch(error => {
console.log(error);
});
