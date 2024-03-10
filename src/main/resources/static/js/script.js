const BASE_URL = 'http://govorigraya.ru/api'
const REVIEW_ENDPOINT = BASE_URL + '/review'
const APPOINTMENT_ENDPOINT = BASE_URL + '/appointment'

/** Форма принимает
    review: string
    name: string
*/
const initReviewForm = () => {
    const form = document.getElementById('review-form')
    const button = document.getElementById('review-form-button')
    const success = document.getElementById('submition-succcess')
    const err = document.getElementById('submition-error')

    form?.addEventListener("submit", e => {
        e.preventDefault()

        button.disabled = true
        button.style.animation = 'pulse infinite 1.5s linear'
        success.style.display = 'none'
        err.style.display = 'none'

        fetch(REVIEW_ENDPOINT, {
            method: 'POST',
            body: new FormData(e.target)
        })
        .then(res => {
            if (res.ok) {
                form.reset()
                success.style.display = 'block'
            } else {
                throw new Error()
            } 
        })
        .catch(res => {
            err.style.display = 'block'
        })
        .finally(() => {
            button.disabled = false
            button.style.animation = 'none'
        })
    })
}

/** Форма принимает
    name: string
    phone: string
    email: string
*/
const initAppointmentForm = () => {
    const form = document.getElementById('appointment-form')
    const button = document.getElementById('action')
    const success = document.getElementById('submition-succcess')
    const err = document.getElementById('submition-error')

    form?.addEventListener("submit", e => {
        button.disabled = true
        success.style.display = 'none'
        button.style.animation = 'pulse infinite 1.5s linear'
        err.style.display = 'none'

        fetch(APPOINTMENT_ENDPOINT, {
            method: 'POST',
            body: new FormData(e.target)
        })
        .then(res => {
            if (res.ok) {
                form.reset()
                success.style.display = 'block'
            } else {
                throw new Error()
            } 
        })
        .catch(res => {
            err.style.display = 'block'
        })
        .finally(() => {
            button.disabled = false
            button.style.animation = 'none'
        })
        e.preventDefault()
    })
}


initReviewForm()
initAppointmentForm()

const hamburger = document.querySelector('.hamburger')
const hamburgerLinks = document.getElementById('hamburger-links')
hamburger.addEventListener('click', e => {
    e.target.classList.toggle('clicked')
    hamburgerLinks.style.display = hamburgerLinks.style.display === 'none' ? 'flex' : 'none'
})
hamburgerLinks.addEventListener('click', e => {
    hamburger.classList.remove('clicked')
    hamburgerLinks.style.display = 'none'
})

const floatingHeader = document.getElementById('floating-header')
floatingHeader && document.addEventListener('scroll', e => {
    if (window.scrollY > 850) {
        floatingHeader.style.pointerEvents = 'auto'
        floatingHeader.style.transform = 'translateY(0%)'
        floatingHeader.style.opacity = '1'
    } else {
        floatingHeader.style.pointerEvents = 'none'
        floatingHeader.style.transform = 'translateY(-20%)'
        floatingHeader.style.opacity = '0'
    }

    if (window.scrollY > 700) {
        floatingHeader.style.maxHeight = floatingHeader.scrollHeight + 'px'
    } else {
        floatingHeader.style.maxHeight = null
    }
})


const acc = document.getElementsByClassName("accordion")
let i;

for (i = 0; i < acc.length; i++) {
    acc[i].addEventListener("click", e=> {
        if ([...e.target.classList].includes('panel')) {

            const content = e.target.getElementsByClassName('content')[0]
            const plus = e.target.getElementsByClassName('plus')[0]
            if (content.style.minHeight) {
                content.style.minHeight = null
                content.style.maxHeight = null
                if (plus) plus.style.transform = 'rotate(0deg)'
            } else {
                content.style.maxHeight = (1.5 * content.scrollHeight) + "px"
                content.style.minHeight = (1.5 * content.scrollHeight) + "px"
                if (plus) plus.style.transform = 'rotate(45deg)'
            }
        }
    })
}

const sidescrolls = document.getElementsByClassName('sidescroll')
for (i = 0; i < sidescrolls.length; i++) {
    const slider = sidescrolls[i]

    let isDown = false
    let startX
    let scrollLeft

    slider.addEventListener('mousedown', (e) => {
        isDown = true
        startX = e.pageX - slider.offsetLeft
        scrollLeft = slider.scrollLeft
    })
    slider.addEventListener('mouseleave', () => {
        isDown = false
    })
    slider.addEventListener('mouseup', () => {
        isDown = false
    })
    slider.addEventListener('mousemove', (e) => {
        if(!isDown) return
        e.preventDefault()
        const x = e.pageX - slider.offsetLeft
        const walk = (x - startX) * 2 //scroll-fast
        slider.scrollLeft = scrollLeft - walk
    })
}
