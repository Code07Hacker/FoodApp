import React from 'react'
import Header from '../../components/Header/Header'
import ExploreMenu from '../../components/ExploreMenu/ExploreMenu'
import FoodDisplay from '../../components/FoodDisplay/FoodDisplay'

const Home = () => {
  return (
    <div className='container'>
        <Header/>
        <ExploreMenu/>
        <FoodDisplay/>
    </div>
  )
}

export default Home