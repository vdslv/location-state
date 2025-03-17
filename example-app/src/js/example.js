import { LocationState } from 'location-state';

window.testEcho = () => {
    const inputValue = document.getElementById("echoInput").value;
    LocationState.echo({ value: inputValue })
}
