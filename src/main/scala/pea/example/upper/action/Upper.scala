package pea.example.upper.action

class Upper(requestName: String) {

  def connect() = new UpperConnectActionBuilder(requestName)
}
