import React from 'react'

const Menubar = ({toggleSideBar}) => {
  return (
    <nav className="navbar navbar-expand-lg navbar-light bg-light border-bottom">
        <div className="container-fluid">
            <button className="btn btn-primary" id="sidebarToggle" onClick={toggleSideBar}>
                <i className='bi bi-list'></i>
            </button>
        </div>
    </nav>
  )
}

export default Menubar