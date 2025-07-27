import React from 'react'
import { categories } from '../../assets/assets'

const ExploreMenu = () => {
  return (
    <div className='explore-menu position-relative'>
      <h1 className='d-flex align-items-centter justify-content-between'>
        Explore Our Menu
        <div className='d-flex'>
          <i className="bi bi-arrow-left-circle"></i>
          <i className='bi bi-arrow-right-circle scroll-icon'></i>
        </div>
      </h1>
      <p>Explore Curated lists of dishes from top categories</p>
      <div className='d-flex justify-content-between gap-4 overflow-auto explore-menu-list'>
        {
          categories.map((item,index)=>{
            return (
            <div key={index} className='text-center explore-menu-list-item'>
              <img src={item.icon} alt='' className='rounded-circle' height={128} width={128}/>
              <p className='mt-2 fw-hold'>{item.category}</p>
            </div>
            )
          })
        }
      </div>
    </div>
  )
}

export default ExploreMenu