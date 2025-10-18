import Link from "next/link"
import { Button } from "@/components/ui/button"
import { PublicationsList } from "@/components/publications-list"
import { PlusCircle } from "lucide-react"

export default function HomePage() {
  return (
    <div className="min-h-screen bg-background">
      <header className="border-b border-border bg-card">
        <div className="container mx-auto px-4 py-6">
          <div className="flex items-center justify-between">
            <div>
              <h1 className="text-3xl font-bold tracking-tight text-foreground">Publications</h1>
              <p className="mt-1 text-sm text-muted-foreground">
                Resilient content management with circuit breaker pattern
              </p>
            </div>
            <Link href="/new">
              <Button size="lg" className="gap-2">
                <PlusCircle className="h-5 w-5" />
                New Post
              </Button>
            </Link>
          </div>
        </div>
      </header>

      <main className="container mx-auto px-4 py-8">
        <PublicationsList />
      </main>
    </div>
  )
}
