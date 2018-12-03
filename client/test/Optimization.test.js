import './enzyme.config.js'
import React from 'react'
import { mount, shallow } from 'enzyme'
import Optimization from '../src/components/Application/Optimization'
import Application from '../src/components/Application/Application'


const startProps = {
    'config': { 'optimization': ['none', 'short', 'shorter', 'shortest'] },
    'options': { 'optimization': 'none' }
};

/* Test example using a pre-defined function */
function testExample() {
    const optimizations = mount((
        <Optimization config={startProps.config} options={startProps.options}/>
    ));

    let actual = [];
    optimizations.find('Button').map((element) => actual.push(element.prop('value')));
    expect(actual).toEqual(startProps.config.optimization);
}

test('Check to see if table gets made correctly (Function)', testExample);