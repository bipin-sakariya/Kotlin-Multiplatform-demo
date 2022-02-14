import SwiftUI
import shared

struct ContentView: View {
	//let greet = Greeting().greeting()
    
    @State var text: String = ""
    @State private var selectedStrength = "Mild"
    let strengths = ["Mild", "Medium", "Mature"]

	var body: some View {
        
		//Text(greet)
        GeometryReader { gr in
            let leftWidth = gr.size.width * 0.35
            let rightWidth = gr.size.width * 0.50
            VStack(spacing : 15){
                HStack{
                    Text("Select Date")
                        .frame(width: leftWidth, alignment: .topLeading)
                        .font(Font.headline.weight(.bold))
                    Text("")
                        .frame(width: rightWidth, height: 20, alignment: .topLeading)
                        .padding()
                        .overlay(RoundedRectangle(cornerRadius: 10).stroke(Color.blue, lineWidth: 3)
                    )
                }
                HStack{
                    Text("Enter Name")
                        .frame(width: leftWidth, alignment: .topLeading)
                        .font(Font.headline.weight(.bold))
                    TextEditor(text: $text)
                        .frame(width: rightWidth, height: 20, alignment: .topLeading)
                        .padding()
                        .overlay(RoundedRectangle(cornerRadius: 10).stroke(Color.blue, lineWidth: 3)
                    )
                }
                HStack{
                    Text("Select Project")
                        .frame(width: leftWidth,alignment: .topLeading)
                        .font(Font.headline.weight(.bold))
                    TextEditor(text: $text)
                        .frame(width: rightWidth, height: 20, alignment: .topLeading)
                        .padding()
                        .overlay(RoundedRectangle(cornerRadius: 10).stroke(Color.blue, lineWidth: 3)
                    )
                
                }
                Button(action: {}) {
                    Text("Add Data")
                        .frame(width: 100)
                        .font(Font.headline.weight(.bold))
                }
                .padding()
                .foregroundColor(.white)
                .background(Color.accentColor)
                .cornerRadius(10)
                List(){
                    ForEach(1..<10) { index in
                        HStack(spacing : 15){
                            VStack(spacing: 8){
                                Text("Date : 10 Feb, 2022")
                                    .frame(maxWidth: .infinity, alignment: .leading)
                                    .font(Font.headline.weight(.medium))
                                Text("Name : Tagline Infotech")
                                    .frame(maxWidth: .infinity, alignment: .leading)
                                    .font(Font.headline.weight(.medium))
                                Text("Project : Development")
                                    .frame(maxWidth: .infinity, alignment: .leading)
                                    .font(Font.headline.weight(.medium))
                            }
                            Image("ic_edit")
                                .resizable()
                                .frame(width: 32, height: 32)
                                .foregroundColor(Color.accentColor)
                            Image("ic_delete")
                                .resizable()
                                .frame(width: 30, height: 30)
                                .foregroundColor(Color.accentColor)
                        }
                    }
                }
                .listStyle(PlainListStyle())
            }
        
        }
        
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
