$(function () {
    const XP_PER_LEVEL = 100;
    let xpTotal = 0;
    let misionesCompletadas = 0;
    let currentLevel = 1;

    function calcLevel(xp) { return Math.floor(xp / XP_PER_LEVEL) + 1; }
    function calcProgress(xp) { return xp % XP_PER_LEVEL; }
    function calcRank(level) {
        if (level >= 7) return 'Experto';
        if (level >= 5) return 'Senior';
        if (level >= 3) return 'Semi-Senior';
        return 'Junior';
    }

    function updateUI(showToast = false) {
        const newLevel = calcLevel(xpTotal);
        const currentXP = calcProgress(xpTotal);
        const rank = calcRank(newLevel);
        const pct = (currentXP / XP_PER_LEVEL) * 100;

        $('#txtNivel').text('Nivel ' + newLevel);
        $('#txtXPActual').text(currentXP);
        $('#statXP').text(xpTotal);
        $('#statMisiones').text(misionesCompletadas);
        $('#badgeRango').text(rank);
        $('#barraXP').css('width', pct + '%');

        // Rank Cards Highlight
        $('.rank-card').removeClass('active-rank');
        $('#rank-' + rank).addClass('active-rank');

        // Level up toast
        if (showToast && newLevel > currentLevel) {
            $('#toastMessage').text('¡Has ascendido al Nivel ' + newLevel + ' y Rango ' + rank + '!');
            $('#toastLevelUp').addClass('show');
            setTimeout(() => $('#toastLevelUp').removeClass('show'), 3500);
        }

        currentLevel = newLevel;
    }

    // Click Quest
    $('#listaMisiones').on('click', '.quest-card', function (e) {
        const $card = $(this);
        if ($card.hasClass('completed')) return;

        $card.addClass('completed');
        const gainedXP = parseInt($card.data('xp'), 10);
        xpTotal += gainedXP;
        misionesCompletadas++;

        // Floating XP Animation
        const $float = $('<div class="xp-float">+' + gainedXP + ' XP</div>').css({
            left: e.pageX + 'px',
            top: (e.pageY - 20) + 'px'
        });
        $('body').append($float);
        setTimeout(() => $float.remove(), 1000);

        updateUI(true);
    });

    // Filter Tabs
    $('.filter-btn').on('click', function () {
        $('.filter-btn').removeClass('active');
        $(this).addClass('active');
        const filter = $(this).data('filter');

        if (filter === 'todas') {
            $('.quest-card').fadeIn(200);
        } else {
            $('.quest-card').hide().filter('[data-tipo="' + filter + '"]').fadeIn(200);
        }
    });

    // Reset Demo
    $('#btnReset').on('click', function () {
        xpTotal = 0;
        misionesCompletadas = 0;
        $('.quest-card').removeClass('completed');
        updateUI(false);
    });

    // Player Form Submit
    $('#formJugador').on('submit', function (e) {
        e.preventDefault();
        const nombre = $('#inputNombre').val();
        if (nombre) {
            $('#nombreJugador').text(nombre);
            const modalEl = document.getElementById('modalJugador');
            if (modalEl && typeof bootstrap !== 'undefined') {
                bootstrap.Modal.getInstance(modalEl)?.hide();
            }
        }
    });

    // Add Custom Quest
    $('#formMision').on('submit', function (e) {
        e.preventDefault();
        const nombre = $('#inputMisionNombre').val();
        const tipo = $('#selectMisionTipo').val();
        const xp = parseInt($('#inputMisionXP').val(), 10);

        const icon = tipo === 'diaria' ? 'fa-bolt' : (tipo === 'semanal' ? 'fa-calendar' : 'fa-folder');
        const $newQuest = $(`
            <div class="quest-card" data-tipo="${tipo}" data-xp="${xp}">
                <div class="quest-check"><i class="fa-solid fa-check"></i></div>
                <div class="quest-info">
                    <div class="quest-title">${nombre}</div>
                    <div class="quest-meta">
                        <span><i class="fa-regular ${icon}"></i> ${tipo.toUpperCase()}</span>
                        <span>•</span>
                        <span>Misión creada por usuario</span>
                    </div>
                </div>
                <div class="quest-xp-tag">+${xp} XP</div>
            </div>
        `);

        $('#listaMisiones').prepend($newQuest);
        const modalEl = document.getElementById('modalNuevaMision');
        if (modalEl && typeof bootstrap !== 'undefined') {
            bootstrap.Modal.getInstance(modalEl)?.hide();
        }
        $('#formMision')[0].reset();
    });

    // Initial load animation
    setTimeout(() => updateUI(false), 200);
});
