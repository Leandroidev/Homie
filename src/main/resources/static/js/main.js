document.addEventListener('DOMContentLoaded', () => {
    const createModal = document.getElementById("createModal");
    const editModal = document.getElementById("editModal");
    const openCreateBtn = document.getElementById("openModalBtn");
    const editBtns = document.querySelectorAll(".edit-btn");
    const closeBtns = document.querySelectorAll(".close-btn");
    const cancelBtns = document.querySelectorAll(".cancel-btn");

    const openModal = (modal) => {
        if (modal) modal.style.display = "block";
    };
    const closeModal = (modal) => {
        if (modal) modal.style.display = "none";
    };

    if (openCreateBtn) {
        openCreateBtn.onclick = () => openModal(createModal);
    }

    editBtns.forEach(btn => {
        btn.onclick = async () => {
            const userId = btn.dataset.id;
            try {
                const response = await fetch(`/api/usuarios/${userId}`);
                if (!response.ok) {
                    const errorData = await response.json();
                    throw new Error(errorData.message || 'Usuario no encontrado');
                }
                const usuario = await response.json();

                const editForm = document.getElementById('editForm');
                editForm.action = `/usuarios/actualizar/${userId}`;
                document.getElementById('edit-nombre').value = usuario.nombre;
                document.getElementById('edit-apellido').value = usuario.apellido;
                document.getElementById('edit-pass').value = '';

                openModal(editModal);

            } catch (error) {
                console.error('Error al cargar datos del usuario:', error);
                alert('No se pudieron cargar los datos para editar: ' + error.message);
            }
        };
    });

    const closeAllModals = () => {
        closeModal(createModal);
        closeModal(editModal);
    };

    closeBtns.forEach(btn => btn.onclick = closeAllModals);
    cancelBtns.forEach(btn => btn.onclick = closeAllModals);

    window.onclick = (event) => {
        if (event.target === createModal) closeModal(createModal);
        if (event.target === editModal) closeModal(editModal);
    };
});